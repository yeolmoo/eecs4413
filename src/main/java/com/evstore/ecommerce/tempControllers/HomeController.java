package com.evstore.ecommerce.tempControllers;

import java.util.ArrayList;
import java.util.List;

import com.evstore.ecommerce.controller.CatalogController;
import com.evstore.ecommerce.model.VehicleProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.repository.CatalogRepository;

// Changes made:
// refactoring this temporary controller to call the existing CatalogController
// which allows access to all catalog API endpoints

@Controller
public class HomeController {

    //private final CatalogRepository catalogRepository;

    //public HomeController(CatalogRepository catalogRepository) {
//        this.catalogRepository = catalogRepository;
//    }

    private final CatalogController cat;

    @Autowired
    public HomeController(CatalogController cat) {
        this.cat = cat;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        //List<Vehicle> vehicles = catalogRepository.findAll();

        List<VehicleProjection> vehicles = cat.getAllVehicles().getBody();
        List<String> brands = cat.getBrands().getBody();
        List<String> shapes = cat.getShapes().getBody();
        List<String> years = cat.getYears().getBody();

        if (vehicles == null) {
            vehicles = new ArrayList<>();
        }
    
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("brands", brands);
        model.addAttribute("shapes", shapes);
        model.addAttribute("years", years);
        model.addAttribute("message", "Welcome to EV Store");
        
        return "home";
    }
}
