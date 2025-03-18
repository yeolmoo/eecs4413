package com.evstore.ecommerce.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.evstore.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "cart") // This maps to MySQL "cart" table
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    
    
    private int quanity; // total items in cart

    
    private BigDecimal totalPrice; // total price of all items in cart.


    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    public Cart() {
        
    }

    public Cart(int quanity, BigDecimal totalPrice, Set<CartItem> cartItems) {
        this.quanity = quanity;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


   


    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    
} 

