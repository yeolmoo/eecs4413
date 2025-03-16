package com.evstore.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity

public class VehicleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private Date recordDate;
    @ManyToOne
    @JoinColumn(name = "vehicle_id") // foreign key
    @JsonBackReference // used to handle bidirectional relationship - child side
    private Vehicle vehicle;

    public VehicleHistory() {
    }

    public VehicleHistory(int id, String description, Date recordDate, Vehicle vehicle) {
        this.id = id;
        this.description = description;
        this.recordDate = recordDate;
        this.vehicle = vehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}