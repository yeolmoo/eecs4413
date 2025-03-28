package com.evstore.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity

public class CustomerReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @Min(1) @Max(5)
    @NotNull(message = "Rating is required")
    private int rating;
    private String reviewText;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;
    @ManyToOne
    @JoinColumn(name = "vehicle_id") // foreign key
    @JsonBackReference
    private Vehicle vehicle;

    public CustomerReview() {
    }

    public CustomerReview(int id, String username, int rating, String reviewText, Date reviewDate, Vehicle vehicle) {
        this.id = id;
        this.username = username;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.vehicle = vehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
