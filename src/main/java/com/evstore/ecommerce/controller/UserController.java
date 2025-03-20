package com.evstore.ecommerce.controller;

import com.evstore.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import com.evstore.ecommerce.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully.");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
//        if (userService.loginUser(username, password, session)) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return ResponseEntity.ok("Login successful");
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<String> logoutUser(HttpSession session) {
//        userService.logoutUser(session);
//        return ResponseEntity.ok("Logout successful");
//    }
}
