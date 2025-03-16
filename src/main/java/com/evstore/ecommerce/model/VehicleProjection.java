package com.evstore.ecommerce.model;

import java.math.BigDecimal;
import java.util.List;

// This interface is used to return a subset of fields when getting a set of vehicles
public interface VehicleProjection {
    int getId();
    String getName();
    String getVehicleCondition();
    BigDecimal getPrice();
    String getBrand();
    String getShape();
    String getModel();
    int getModelYear();
    boolean getHotDeal();
    BigDecimal getDiscount();
    String getVehicleImg();
    BigDecimal getMileage(); // only used for sorting, not displaying
}
