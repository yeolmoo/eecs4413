package com.evstore.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.repository.CatalogRepository;

@Controller
public class HomeController {

    private final CatalogRepository catalogRepository;

    public HomeController(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Vehicle> vehicles = catalogRepository.findAll(); 
    
        if (vehicles == null) {
            vehicles = new ArrayList<>();
        }
    
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("message", "Welcome to EV Store");
        
        return "home";
    }
}
