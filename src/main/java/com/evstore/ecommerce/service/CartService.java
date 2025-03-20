package com.evstore.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.evstore.ecommerce.model.Cart;
import com.evstore.ecommerce.repository.CartRepository;
import com.evstore.ecommerce.repository.CatalogRepository;
import com.evstore.ecommerce.repository.UserRepository;

public class CartService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CatalogRepository catalogRepository;

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
