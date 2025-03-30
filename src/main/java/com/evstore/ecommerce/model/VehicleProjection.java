package com.evstore.ecommerce.model;

// This interface is used to return a subset of fields when getting a set of vehicles
public interface VehicleProjection {
    int getId();
    String getName();
    String getVehicleCondition();
    Double getPrice();
    String getBrand();
    String getShape();
    String getModel();
    int getModelYear();
    Boolean getHotDeal();
    Double getDiscount();
    String getVehicleImg();
    Double getMileage(); // only used for sorting, not displaying
}
