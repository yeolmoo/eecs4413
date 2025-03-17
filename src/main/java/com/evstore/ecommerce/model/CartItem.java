package com.evstore.ecommerce.model;

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

    public CartItem(){}
    
    
    public CartItem(Vehicle car, Cart cart, int quanity) {
        this.car = car;
        this.cart = cart;
        this.quanity = quanity;
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

}
