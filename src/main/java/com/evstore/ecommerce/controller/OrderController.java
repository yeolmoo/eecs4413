package com.evstore.ecommerce.controller;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evstore.ecommerce.dto.CheckoutRequestDTO;
import com.evstore.ecommerce.dto.PurchaseOrderDTO;
import com.evstore.ecommerce.model.CustomUserDetails;
import com.evstore.ecommerce.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequestDTO checkoutDTO,
                                      @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            service.processCheckoutForm(checkoutDTO, userDetails.getUser());
            return ResponseEntity.ok().body("Order placed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Checkout failed.");
        }
    }


    // Unique use case: allows users to see their previous orders
    // @GetMapping("/history")
    // public ResponseEntity<List<PurchaseOrder>> getOrderHistory(Principal principal) {
    //     List<PurchaseOrder> history = service.getOrderHistory(principal.getName());

    //     if (history.isEmpty()) return ResponseEntity.noContent().build();

    //     return ResponseEntity.ok(history);
    // }
    @GetMapping("/history")
public ResponseEntity<List<PurchaseOrderDTO>> getOrderHistory(Principal principal) {
    List<PurchaseOrderDTO> history = service.getOrderHistory(principal.getName());

    System.out.println("Fetched order history for " + principal.getName() + ": " + history.size() + " orders");

    if (history.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(history);
}



}
