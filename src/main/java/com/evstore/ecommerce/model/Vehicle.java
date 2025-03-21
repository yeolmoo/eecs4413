package com.evstore.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String vehicleCondition;
	private double price;
	private int stock;
	private double mileage;
	private String brand;
	private String shape;
	private String model;
	private int modelYear;
	private boolean hotDeal;
	private double discount;
	private boolean havingHistory;

	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<VehicleHistory> histories;

	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<CustomerReview> reviews;

	private String vehicleImg;

	public Vehicle() {
	}

	public Vehicle(int id, String name, String description, String vehicleCondition, double price, int stock,
			double mileage, String brand, String shape, String model, int modelYear, boolean hotDeal,
			double discount, boolean havingHistory, Set<VehicleHistory> histories, Set<CustomerReview> reviews,
			String vehicleImg) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.vehicleCondition = vehicleCondition;
		this.price = price;
		this.stock = stock;
		this.mileage = mileage;
		this.brand = brand;
		this.shape = shape;
		this.model = model;
		this.modelYear = modelYear;
		this.hotDeal = hotDeal;
		this.discount = discount;
		this.havingHistory = havingHistory;
		this.histories = histories;
		this.reviews = reviews;
		this.vehicleImg = vehicleImg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVehicleCondition() {
		return vehicleCondition;
	}

	public void setVehicleCondition(String vehicleCondition) {
		this.vehicleCondition = vehicleCondition;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public boolean getHotDeal() {
		return hotDeal;
	}

	public void setHotDeal(boolean hotDeal) {
		this.hotDeal = hotDeal;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean getHavingHistory() {
		return havingHistory;
	}

	public void setHavingHistory(boolean havingHistory) {
		this.havingHistory = havingHistory;
	}

	public Set<VehicleHistory> getHistories() {
		return histories;
	}

	public void setHistories(Set<VehicleHistory> histories) {
		this.histories = histories;
	}

	public Set<CustomerReview> getReviews() {
		return reviews;
	}

	public void setReviews(Set<CustomerReview> reviews) {
		this.reviews = reviews;
	}

	public String getVehicleImg() {
		return vehicleImg;
	}

	public void setVehicleImg(String vehicleImg) {
		this.vehicleImg = vehicleImg;
	}
}
