package com.evstore.ecommerce.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evstore.ecommerce.model.Cart;
import com.evstore.ecommerce.model.CartItem;
import com.evstore.ecommerce.model.CartItemRequest;
import com.evstore.ecommerce.model.User;
import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.repository.CartItemRepository;
import com.evstore.ecommerce.repository.CartRepository;
import com.evstore.ecommerce.repository.CatalogRepository;
import com.evstore.ecommerce.repository.UserRepository;

@Service
public class CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public void addToCart(String username, CartItemRequest request) {
       // Get currently logged-in user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the user's cart. If it doesn't exist, instantiate a new cart and assign to the user
        Cart cart = cartRepository.findByUser(user)
                 .orElseGet(() -> {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setCartItems(new ArrayList<>()); 
                return cartRepository.save(newCart);
            });


        // Find vehicle that user is requesting to add to cart
        Vehicle vehicle = catalogRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Create CartItem, populate and save
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(request.getQuantity());
        cartItem.setPrice(vehicle.getPrice() * request.getQuantity());
        cartItem.setCart(cart);
        cartItem.setVehicle(vehicle);
        cartItem.setCustomization(request.getCustomization());
        cartItemRepository.save(cartItem);

        if (cart.getCartItems() == null) {
            cart.setCartItems(new ArrayList<>()); 
        }
        // Add CartItem to Cart's collection (in-memory)
        cart.getCartItems().add(cartItem);

        // Update the cart's total price
        updateTotalPrice(cart);
        cartRepository.save(cart);
    }

    public void removeFromCart(String username, int cartItemId) {
        // Get currently logged-in user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the user's cart
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Find the CartItem to remove
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        // Check that the CartItem belongs to the current user's cart
        if (cartItem.getCart() != cart) {
            throw new RuntimeException("CartItem does not belong to the current cart");
        }

        // Remove the CartItem from memory and in database
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        // If the cart is now empty, delete it
        if (cart.getCartItems().isEmpty()) {
            cartRepository.delete(cart);
        }
        else {
            updateTotalPrice(cart); // Update the cart's total price and save
            cartRepository.save(cart);
        }
    }

    public void updateTotalPrice(Cart cart) {
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();

        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }

    public void editQuantity(String username, int cartItemId, int quantity) {
        // Get currently logged-in user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the user's cart
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Find the CartItem to update
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        // Check that the CartItem belongs to the current user's cart
        if (cartItem.getCart() != cart) {
            throw new RuntimeException("CartItem does not belong to the current cart");
        }

        // Update the quantity of the CartItem
        cartItem.setQuantity(quantity);

        // Recalculate the price based on the new quantity
        cartItem.setPrice(cartItem.getVehicle().getPrice() * quantity);

        // Save the updated CartItem
        cartItemRepository.save(cartItem);

        // Update the cart's total price
        updateTotalPrice(cart);
        cartRepository.save(cart);

    }

    public Cart getCart(String username) {
        // Get currently logged-in user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElse(null);
        if (cart != null) {
            updateTotalPrice(cart);
        }
        // Get the user's cart
        return cart;

    }
}
