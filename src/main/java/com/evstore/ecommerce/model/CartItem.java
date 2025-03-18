package com.evstore.ecommerce.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_item") // This maps to MySQL "cart_items" table
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @ManyToOne
    private Vehicle car;
    
    @ManyToOne
    private Cart cart;


    private int quanity;
    
    private BigDecimal price;


    public CartItem(){}

    public CartItem(Vehicle car, Cart cart, int quanity, BigDecimal price) {
        this.car = car;
        this.cart = cart;
        this.quanity = quanity;
        this.price = price;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


    public int getQuanity() {
        return quanity;
    }


    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public Vehicle getCar() {
        return car;
    }


    public void setCar(Vehicle car) {
        this.car = car;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
