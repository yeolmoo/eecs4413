package com.evstore.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String province;
    @NotNull
    private String country;
    @NotNull
    private String zip;
    @NotNull
    private String phoneNum;
    @NotNull
    private boolean billing;

    public Address() {
    }

    public Address(int id, String street, String city, String province, String country, String zip, String phoneNum, boolean billing) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.phoneNum = phoneNum;
        this.billing = billing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isBilling() {
        return billing;
    }

    public void setBilling(boolean billing) {
        this.billing = billing;
    }
}
