package com.evstore.ecommerce.controller;

import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.model.VehicleProjection;
import com.evstore.ecommerce.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // loan calculator
    // compare vehicles
    // add reviews
    // customization options
    // make sure to add all CRUD commands
}
