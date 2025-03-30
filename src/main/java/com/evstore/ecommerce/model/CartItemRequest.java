package com.evstore.ecommerce.model;

// DTO for Add to Cart request, with only those attributes of CartItem that can be passed from the user
public class CartItemRequest {
    private int quantity;
    private int vehicleId;
    private Customization customization;

    public CartItemRequest() {
    }

    public CartItemRequest(int quantity, int vehicleId, Customization customization) {
        this.quantity = quantity;
        this.vehicleId = vehicleId;
        this.customization = customization;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Customization getCustomization() {
        return customization;
    }

    public void setCustomization(Customization customization) {
        this.customization = customization;
    }
}
