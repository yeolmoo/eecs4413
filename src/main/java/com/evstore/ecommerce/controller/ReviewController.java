package com.evstore.ecommerce.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evstore.ecommerce.dto.ReviewRequest;
import com.evstore.ecommerce.model.CustomUserDetails;
import com.evstore.ecommerce.model.CustomerReview;
import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.repository.CatalogRepository;
import com.evstore.ecommerce.repository.ReviewRepository;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @PostMapping("/{vehicleId}")
    public ResponseEntity<?> submitReview(@PathVariable Integer vehicleId,
                                          @RequestBody ReviewRequest request,
                                          @AuthenticationPrincipal CustomUserDetails userDetails) {
        Vehicle vehicle = catalogRepository.findById(vehicleId).orElse(null);
        if (vehicle == null) {
            return ResponseEntity.badRequest().body("Invalid vehicle ID.");
        }

        CustomerReview review = new CustomerReview();
        review.setUsername(userDetails.getUsername()); 
        review.setRating(request.getRating());
        review.setReviewText(request.getReviewText());
        review.setReviewDate(new Date());
        review.setVehicle(vehicle);

        reviewRepository.save(review);
        return ResponseEntity.ok("Review submitted successfully.");
    }
}
