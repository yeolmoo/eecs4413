package com.evstore.ecommerce.service;
import com.evstore.ecommerce.model.CustomerReview;
import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.model.VehicleHistory;
import com.evstore.ecommerce.model.VehicleProjection;
import com.evstore.ecommerce.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class CatalogService {
    @Autowired
    private CatalogRepository repo;

    public List<VehicleProjection> getAllVehicles() {
        return repo.findAllBy();
    }

    public Vehicle getVehicle(int id) {
        Vehicle vehicle = repo.findWithDetailsById(id).orElse(null);

        // Sort history report and reviews by descending date
        if (vehicle != null) {
            Set<VehicleHistory> sortedHistories = new TreeSet<>(Comparator.comparing(VehicleHistory::getRecordDate).reversed());
            sortedHistories.addAll(vehicle.getHistories());  // This automatically sorts by date

            vehicle.setHistories(sortedHistories);

            Set<CustomerReview> sortedReviews = new TreeSet<>(Comparator.comparing(CustomerReview::getReviewDate).reversed());
            sortedReviews.addAll(vehicle.getReviews());  // This automatically sorts by review date

            vehicle.setReviews(sortedReviews);
        }

        return vehicle;
    }

    public List<String> getAvailableBrands() {
        return repo.findDistinctBrands();
    }

    public List<String> getAvailableShapes() {
        return repo.findDistinctShapes();
    }

    public List<String> getAvailableModelYears() {
        return repo.findDistinctModelYears();
    }

    public List<VehicleProjection> findByFilters(String brand, String shape, Integer modelYear, Boolean havingHistory, String vehicleCondition, String sortBy, Boolean descending) {
        if (brand != null) {
            brand = brand.toUpperCase();
        }
        if (shape != null) {
            shape = shape.toUpperCase();
        }
        if (vehicleCondition != null) {
            vehicleCondition = vehicleCondition.toUpperCase();
        }
        List<VehicleProjection> vehicles = repo.findByFilters(brand, shape, modelYear, havingHistory, vehicleCondition);

        // Apply sorting by price/mileage if selected
        if (sortBy != null) {
            Comparator<VehicleProjection> comparator = switch (sortBy.toLowerCase()) {
                case "price" -> Comparator.comparing(VehicleProjection::getPrice);
                case "mileage" -> Comparator.comparing(VehicleProjection::getMileage);
                default -> null;
            };

            if (comparator != null) {
                if (descending) {
                    comparator = comparator.reversed();
                }
                vehicles.sort(comparator);
            }
        }
        return vehicles;
    }

    public List<VehicleProjection> getDeals() {
        return repo.findByHotDealTrueOrderByPriceAsc();
    }
}
