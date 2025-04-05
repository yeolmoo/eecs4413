package com.evstore.ecommerce.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evstore.ecommerce.model.CartItemRequest;
import com.evstore.ecommerce.service.CartService;

@CrossOrigin(
    origins = {
        "http://localhost:3000",
        "http://localhost:3002",
        "https://eecs-evstore.netlify.app"
    },
    allowCredentials = "true"
)
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest request, Principal principal) {
        try {
            cartService.addToCart(principal.getName(), request);
            return ResponseEntity.ok("Vehicle successfully added to cart.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<?> removeFromCart(@PathVariable int cartItemId, Principal principal) {
        try {
            cartService.removeFromCart(principal.getName(), cartItemId);
            return ResponseEntity.ok("Vehicle successfully removed from cart.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editQuantity/{cartItemId}")
    public ResponseEntity<?> editQuantity(@PathVariable int cartItemId, @RequestParam int quantity, Principal principal) {
        try {
            cartService.editQuantity(principal.getName(), cartItemId, quantity);
            return ResponseEntity.ok("CartItem quantity updated successfully.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getCart(Principal principal) {
        try {
            return new ResponseEntity<>(cartService.getCart(principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
