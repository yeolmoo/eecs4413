package com.evstore.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Customization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String exteriorColor;
    private String interiorColor;
    private String interiorFabric;

    public Customization() {
    }

    public Customization(int id, String exteriorColor, String interiorColor, String interiorFabric) {
        this.id = id;
        this.exteriorColor = exteriorColor;
        this.interiorColor = interiorColor;
        this.interiorFabric = interiorFabric;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getInteriorFabric() {
        return interiorFabric;
    }

    public void setInteriorFabric(String interiorFabric) {
        this.interiorFabric = interiorFabric;
    }
}
