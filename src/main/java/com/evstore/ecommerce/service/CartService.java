package com.evstore.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.evstore.ecommerce.model.Cart;
import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.repository.CartRepository;
import com.evstore.ecommerce.repository.VehicleRepository;
import com.evstore.ecommerce.user.User;
import com.evstore.ecommerce.userrepository.UserRepository;

public class CartService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CartRepository cartRepository;


private Cart cart;

public CartService() {
    this.cart = cart;
}

public Cart getCart() {
    return cart;
}






}
