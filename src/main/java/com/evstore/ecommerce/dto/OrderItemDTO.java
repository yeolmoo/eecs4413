package com.evstore.ecommerce.dto;

import com.evstore.ecommerce.model.OrderItem;

public class OrderItemDTO {
    private int quantity;
    private double price;
    private VehicleDTO vehicle;

    public OrderItemDTO() {}

    public OrderItemDTO(int quantity, double price, VehicleDTO vehicle) {
        this.quantity = quantity;
        this.price = price;
        this.vehicle = vehicle;
    }

    public OrderItemDTO(OrderItem item) {
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.vehicle = new VehicleDTO(item.getVehicle());
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
}
