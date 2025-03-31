package com.evstore.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evstore.ecommerce.dto.CheckoutRequestDTO;
import com.evstore.ecommerce.dto.PurchaseOrderDTO;
import com.evstore.ecommerce.model.Address;
import com.evstore.ecommerce.model.Cart;
import com.evstore.ecommerce.model.CartItem;
import com.evstore.ecommerce.model.OrderItem;
import com.evstore.ecommerce.model.PurchaseOrder;
import com.evstore.ecommerce.model.User;
import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.repository.CartRepository;
import com.evstore.ecommerce.repository.CatalogRepository;
import com.evstore.ecommerce.repository.OrderRepository;
import com.evstore.ecommerce.repository.UserRepository;

import jakarta.transaction.Transactional;

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

    private final Map<String, AtomicInteger> userPaymentRequestCounters = new ConcurrentHashMap<>();

    public boolean processPayment(String username) {
        AtomicInteger counter = userPaymentRequestCounters.computeIfAbsent(username, k -> new AtomicInteger(0));
        int count = counter.incrementAndGet();
        return count % 3 != 0;
    }

    @Transactional
public void processCheckoutForm(CheckoutRequestDTO dto, User user) {
    Cart cart = cartRepository.findByUser(user)
            .orElseThrow(() -> new RuntimeException("Cart not found"));

    if (cart.getCartItems().isEmpty()) {
        throw new RuntimeException("Cart is empty.");
    }

    if (!processPayment(user.getUsername())) {
        throw new RuntimeException("Credit Card Authorization Failed.");
    }

  
    Address billingAddress = new Address();
    billingAddress.setStreet(dto.getAddress());
    billingAddress.setCity(dto.getCity());
    billingAddress.setProvince(dto.getProvince());
    billingAddress.setCountry(dto.getCountry());
    billingAddress.setZip(dto.getPostalCode());
    billingAddress.setPhoneNum(dto.getPhone()); 

 
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setUser(user);
    purchaseOrder.setTotalPrice(cart.getTotalPrice());
    purchaseOrder.setBillingAddress(billingAddress);
    purchaseOrder.setOrderDate(LocalDateTime.now());

    List<OrderItem> orderItems = new ArrayList<>();

    for (CartItem cartItem : cart.getCartItems()) {
        Vehicle vehicle = cartItem.getVehicle();
        if (vehicle.getStock() < cartItem.getQuantity()) {
            throw new RuntimeException("Insufficient stock for: " + vehicle.getName());
        }

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

    System.out.println("Order placed by: " + dto.getFullName() + " - " + dto.getEmail());
    System.out.println("Shipping to: " + dto.getAddress() + ", " + dto.getCity());

    orderRepository.save(purchaseOrder);
    cartRepository.delete(cart);
    
}
    public List<PurchaseOrderDTO> getOrderHistory(String username) {
        List<PurchaseOrder> orders = orderRepository.findByUserUsernameOrderByOrderDateDesc(username);
        return orders.stream()
                .map(PurchaseOrderDTO::new)
                .collect(Collectors.toList());
    }
}
