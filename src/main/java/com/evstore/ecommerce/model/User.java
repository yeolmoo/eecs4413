package com.evstore.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    @Email
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;

    @OneToMany
    @JoinColumn(name = "address_id")
    private List<Address> addresses;

    // Maybe not that necessary
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private Cart cart;

    public User() {
    }

    public User(Long id, String username, String email, String password, List<Address> addresses /*, Cart cart */) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.addresses = addresses;
//        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
}
