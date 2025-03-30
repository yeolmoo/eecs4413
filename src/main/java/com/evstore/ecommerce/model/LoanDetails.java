package com.evstore.ecommerce.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// DTO for loan calculator
public class LoanDetails {
    @NotNull(message = "Vehicle price is required")
    @Min(value = 1, message = "Vehicle price must be greater than zero")
    private double vehiclePrice;
    @NotNull(message = "Down payment is required")
    @Min(value = 0, message = "Down payment cannot be negative")
    private double downPayment;
    @NotNull(message = "Interest rate is required")
    @Min(value = 0, message = "Interest rate cannot be negative")
    private double interestRate;
    @NotNull(message = "Loan duration is required")
    @Min(value = 1, message = "Loan duration must be at least 1 month")
    private int loanDuration;

    public LoanDetails() {
    }

    public LoanDetails(double vehiclePrice, double downPayment, double interestRate, int loanDuration) {
        this.vehiclePrice = vehiclePrice;
        this.downPayment = downPayment;
        this.interestRate = interestRate;
        this.loanDuration = loanDuration;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }
}
