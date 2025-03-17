package com.evstore.ecommerce.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "1234"; // Replace with your actual password
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Hashed Password: " + encodedPassword);
    }
}