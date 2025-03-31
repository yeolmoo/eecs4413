package com.evstore.ecommerce.dto;

import com.evstore.ecommerce.model.Vehicle;

public class VehicleDTO {
    private int id;
    private String name;
    private double price;
    private String brand;
    private String model;
    private int modelYear;
    private String vehicleImg;

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.name = vehicle.getName();
        this.price = vehicle.getPrice();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.modelYear = vehicle.getModelYear();
        this.vehicleImg = vehicle.getVehicleImg();
    }

    public VehicleDTO() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public String getVehicleImg() {
        return vehicleImg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public void setVehicleImg(String vehicleImg) {
        this.vehicleImg = vehicleImg;
    }

}
