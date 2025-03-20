package com.evstore.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle car;

    @OneToOne
    @JoinColumn(name = "customization_id")
    private Customization cust;

    public CartItem() {
    }

    public CartItem(Long id, Cart cart, Vehicle car, int quantity, double price, Customization cust) {
        this.id = id;
        this.cart = cart;
        this.car = car;
        this.quantity = quantity;
        this.price = price;
        this.cust = cust;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Vehicle getCar() {
        return car;
    }

    public void setCar(Vehicle car) {
        this.car = car;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customization getCust() {
        return cust;
    }

    public void setCust(Customization cust) {
        this.cust = cust;
    }
}
