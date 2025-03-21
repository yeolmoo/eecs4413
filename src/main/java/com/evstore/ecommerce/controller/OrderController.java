package com.evstore.ecommerce.controller;
import com.evstore.ecommerce.model.Address;
import com.evstore.ecommerce.model.PurchaseOrder;
import com.evstore.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody Address billingAddress, Principal principal) {
        try {
            String message = service.checkout(billingAddress, principal.getName());
            return ResponseEntity.ok(message);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Unique use case: allows users to see their previous orders
    @GetMapping("/history")
    public ResponseEntity<List<PurchaseOrder>> getOrderHistory(Principal principal) {
        List<PurchaseOrder> history = service.getOrderHistory(principal.getName());

        if (history.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(history);
    }
}
