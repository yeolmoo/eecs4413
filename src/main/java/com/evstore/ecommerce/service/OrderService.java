package com.evstore.ecommerce.service;

import com.evstore.ecommerce.model.*;
import com.evstore.ecommerce.repository.CartRepository;
import com.evstore.ecommerce.repository.CatalogRepository;
import com.evstore.ecommerce.repository.OrderRepository;
import com.evstore.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CatalogRepository catalogRepository;

    // Used to count the number of
    private final Map<String, AtomicInteger> userPaymentRequestCounters = new ConcurrentHashMap<>();

    // Used to approve two consecutive payment requests and deny every 3rd
    public boolean processPayment(String username) {
        AtomicInteger counter = userPaymentRequestCounters.computeIfAbsent(username, k -> new AtomicInteger(0));
        int count = counter.incrementAndGet();
        return count % 3 != 0;
    }

    @Transactional
    public String checkout(Address billingAddress, String username) {
        // Get currently logged-in user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the user's cart
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Check if cart is empty
        if (cart.getCartItems().isEmpty()) {
            return "Cart is empty. Add items to continue to checkout.";
        }

        // Check if payment is approved
        boolean paymentApproved = processPayment(username);
        if (!paymentApproved) {
            return "Credit Card Authorization Failed.";
        }

        // Note: Credit card input validation is required in frontend
        // since payment details will not be sent to the backend
        // Also do billing address validation in frontend

        // Create order, populate with info from cart, and save
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setTotalPrice(cart.getTotalPrice());
        purchaseOrder.setUser(user);
        purchaseOrder.setBillingAddress(billingAddress);
        purchaseOrder.setOrderDate(java.sql.Date.valueOf(LocalDate.now()));

        List<OrderItem> orderItems = new ArrayList<>();

        // Ensure that vehicles have enough stock left
        for (CartItem cartItem : cart.getCartItems()) {
            Vehicle vehicle = cartItem.getVehicle();
            if (vehicle.getStock() < cartItem.getQuantity()) {
                return "Insufficient stock for: " + vehicle.getName();
            }
            // Update vehicle stock
            vehicle.setStock(vehicle.getStock() - cartItem.getQuantity());
            catalogRepository.save(vehicle);

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setPurchaseOrder(purchaseOrder);
            orderItem.setVehicle(vehicle);
            orderItem.setCustomization(cartItem.getCustomization());
            orderItems.add(orderItem);
        }

        purchaseOrder.setOrderItems(orderItems);
        orderRepository.save(purchaseOrder);

        // Empty the cart (delete it)
        cartRepository.delete(cart);

        return "Order Successfully Completed.";
    }

    public List<PurchaseOrder> getOrderHistory(String username) {
        return orderRepository.findByUserUsernameOrderByOrderDateDesc(username);
    }


}
