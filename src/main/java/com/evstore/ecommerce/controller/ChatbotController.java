package com.evstore.ecommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evstore.ecommerce.service.ChatbotService;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    @Autowired
    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

 @PostMapping("/message")
    public ResponseEntity<String> getChatbotResponse(@RequestBody Map<String, String> request) {
        String question = request.get("message");
        String answer = chatbotService.ask(question);
        return ResponseEntity.ok(answer);
    }
}
