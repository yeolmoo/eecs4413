package com.evstore.ecommerce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evstore.ecommerce.model.VehicleProjection; // ✅ Use VehicleProjection instead
import com.evstore.ecommerce.repository.CatalogRepository;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final CatalogRepository catalogRepository;

    public ProductsController(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @GetMapping
    public String showProductsPage(Model model) {
        List<VehicleProjection> vehicles = catalogRepository.findAllByOrderById();
        model.addAttribute("vehicles", vehicles);
        return "products"; 
    }
}
