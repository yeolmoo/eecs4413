package com.evstore.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.evstore.ecommerce.service.CartService;
import com.evstore.ecommerce.user.User;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam User user,@RequestParam int carID, @RequestParam int quanity) {
        //TODO: process POST request

        
        cartService.addToCart(user, carID, quanity);
        return ResponseEntity.ok("Vehicle successfully added");
    }


    @PostMapping("/remove")
    public ResponseEntity<String> removeFromToCart(@RequestParam User user,@RequestParam int carID) {
        //TODO: process POST request
        
        
        cartService.removeFromCart(user, carID);

        return ResponseEntity.ok("Vehicle successfully removed");
    }
    

}
