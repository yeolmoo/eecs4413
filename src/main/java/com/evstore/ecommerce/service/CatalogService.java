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
		return repo.findAllByOrderById();
	}

	public Vehicle getVehicle(int id) {
		Vehicle vehicle = repo.findWithDetailsById(id).orElse(null);

		// Sort history report and reviews by descending date
		if (vehicle != null) {
			vehicle.setHistories(vehicle.getHistories().stream()
					.sorted(Comparator.comparing(VehicleHistory::getRecordDate).reversed())
					.collect(Collectors.toCollection(TreeSet::new)));

			vehicle.setReviews(
					vehicle.getReviews().stream().sorted(Comparator.comparing(CustomerReview::getReviewDate).reversed())
							.collect(Collectors.toCollection(TreeSet::new)));
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

	public List<VehicleProjection> findByFilters(String brand, String shape, Integer modelYear, Boolean havingHistory,
			String vehicleCondition, String sortBy, Boolean descending) {
		List<VehicleProjection> vehicles = repo.findByFilters(brand != null ? brand.toUpperCase() : null,
				shape != null ? shape.toUpperCase() : null, modelYear, havingHistory,
				vehicleCondition != null ? vehicleCondition.toUpperCase() : null);

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
		return repo.findHotDeals();
	}
}
