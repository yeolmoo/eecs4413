package com.evstore.ecommerce.controller;

import com.evstore.ecommerce.model.*;
import com.evstore.ecommerce.service.CatalogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private CatalogService service;

    // Get entire catalog of vehicles
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleProjection>> getAllVehicles() {
        return new ResponseEntity<>(service.getAllVehicles(), HttpStatus.OK);
    }

    // Get detailed info on one vehicle
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable int id) {
        Vehicle vehicle = service.getVehicle(id);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get list of brands available in catalog
    @GetMapping("/brands")
    public ResponseEntity<List<String>> getBrands() {
        return new ResponseEntity<>(service.getAvailableBrands(), HttpStatus.OK);
    }

    // Get list of shapes available in catalog
    @GetMapping("/shapes")
    public ResponseEntity<List<String>> getShapes() {
        return new ResponseEntity<>(service.getAvailableShapes(), HttpStatus.OK);
    }

    // Get list of years available in catalog
    @GetMapping("/years")
    public ResponseEntity<List<String>> getYears() {
        return new ResponseEntity<>(service.getAvailableModelYears(), HttpStatus.OK);
    }

    // Apply various filters and sorting to get subset of catalog items
    @GetMapping("/search")
    public ResponseEntity<List<VehicleProjection>> searchVehicles (
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String shape,
            @RequestParam(required = false) Integer modelYear,
            @RequestParam(required = false) Boolean havingHistory,
            @RequestParam(required = false) String vehicleCondition,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "false") Boolean descending) {
        return new ResponseEntity<>(service.findByFilters(brand, shape, modelYear, havingHistory, vehicleCondition, sortBy, descending), HttpStatus.OK);
    }

    // Get hot deals
    @GetMapping("/deals")
    public ResponseEntity<List<VehicleProjection>> getDeals() {
        return new ResponseEntity<>(service.getDeals(), HttpStatus.OK);
    }

    // Add new vehicle to catalog
    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            service.addVehicle(vehicle);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add new review under specified vehicle
    @PostMapping("/{id}/review")
    public ResponseEntity<?> addReview(@PathVariable int id, @RequestBody @Valid CustomerReview review) {
        Vehicle vehicle = service.getVehicle(id);
        if (vehicle != null) {
            try {
                service.addReview(id, review);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add new history under specified vehicle
    @PostMapping("/{id}/history")
    public ResponseEntity<?> addHistory(@PathVariable int id, @RequestBody VehicleHistory history) {
        Vehicle vehicle = service.getVehicle(id);
        if (vehicle != null) {
            try {
                service.addHistory(id, history);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update any field(s) of vehicle at once (except for reviews and histories)
    @PutMapping("/vehicle/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Vehicle updatedVehicle = service.updateVehicle(id, updates);
        return updatedVehicle != null ? ResponseEntity.ok(updatedVehicle) : ResponseEntity.notFound().build();
    }

    // Delete a specified vehicle
    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable int id) {
        Vehicle vehicle = service.getVehicle(id);
        if (vehicle != null) {
            service.deleteVehicle(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/calculate")
    public BigDecimal calculateLoan(@RequestBody @Valid LoanDetails loanDetails) {
        return service.calculateMonthlyPayment(
                loanDetails.getVehiclePrice(),
                loanDetails.getDownPayment(),
                loanDetails.getInterestRate(),
                loanDetails.getLoanDuration()
        );
    }

    @GetMapping("/compare/{id}")
    public ResponseEntity<VehicleProjection> getVehicleToCompare(@PathVariable int id) {
        Vehicle vehicle = service.getVehicle(id);
        if (vehicle != null) {
            return new ResponseEntity<>(service.getSimilarVehicle(vehicle), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DONE:
    // C, R, U, D - vehicle
    // C, R - review and history (will omit U and D for simplicity)
    // R - filter categories (the rest is done through vehicles)
    // Loan calculator
    // Compare Vehicles: Get similar vehicle for comparison

}
