package com.evstore.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.evstore.ecommerce.model.PurchaseOrder;

public class PurchaseOrderDTO {
    private int id;
    private double totalPrice;
    private String status;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> items;

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public PurchaseOrderDTO() {}
    
    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public PurchaseOrderDTO(PurchaseOrder order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.status = order.getStatus();
        this.orderDate = order.getOrderDate();
        this.items = order.getOrderItems().stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());
    }

    
}
