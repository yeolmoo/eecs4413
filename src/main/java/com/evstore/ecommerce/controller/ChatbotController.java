package com.evstore.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    @PostMapping("/message")
    public String getChatbotResponse(@RequestParam("message") String message) {
        String lowerCaseMessage = message.toLowerCase();

        if (lowerCaseMessage.contains("hello") || lowerCaseMessage.contains("hi")) {
            return "Hello! How can I assist you today?";
        } else if (lowerCaseMessage.contains("order")) {
            return "You can check your order status on your profile page.";
        } else if (lowerCaseMessage.contains("discount")) {
            return "Check our latest promotions on the products page!";
        } else if (lowerCaseMessage.contains("price")) {
            return "You can find product prices on the browse page.";
        } else if (lowerCaseMessage.contains("help")) {
            return "Sure! What do you need help with?";
        } else {
            return "I'm not sure. Try asking about orders, products, or discounts!";
        }
    }
    //TODO: Add response

    
}
