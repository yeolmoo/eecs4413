package com.evstore.ecommerce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.evstore.ecommerce.model.VehicleProjection;
import com.evstore.ecommerce.repository.CatalogRepository;

@Controller
public class HomeController {

    private final CatalogRepository catalogRepository;

    public HomeController(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<VehicleProjection> vehicles = catalogRepository.findAllByOrderById(); // 🚀 여기가 핵심
        model.addAttribute("vehicles", vehicles); 
        return "home"; 
    }
}
