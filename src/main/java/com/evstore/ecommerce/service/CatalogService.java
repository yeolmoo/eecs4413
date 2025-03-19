package com.evstore.ecommerce.service;

import com.evstore.ecommerce.model.CustomerReview;
import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.model.VehicleHistory;
import com.evstore.ecommerce.model.VehicleProjection;
import com.evstore.ecommerce.repository.CatalogRepository;
import com.evstore.ecommerce.repository.HistoryRepository;
import com.evstore.ecommerce.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CatalogService {
	@Autowired
	private CatalogRepository repo;
	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private HistoryRepository historyRepo;

	public List<VehicleProjection> getAllVehicles() {
		return repo.findAllByOrderById();
	}

	public Vehicle getVehicle(int id) {
		Vehicle vehicle = repo.findWithDetailsById(id).orElse(null);

		// Sort history report and reviews by descending date
		if (vehicle != null) {
			Set<VehicleHistory> sortedHistories = new TreeSet<>(Comparator.comparing(VehicleHistory::getRecordDate).reversed());
			sortedHistories.addAll(vehicle.getHistories());

			vehicle.setHistories(sortedHistories);

			Set<CustomerReview> sortedReviews = new TreeSet<>(Comparator.comparing(CustomerReview::getReviewDate).reversed());
			sortedReviews.addAll(vehicle.getReviews());

			vehicle.setReviews(sortedReviews);
		}
		// This version below caused ClassCastException error, changed back to original above
		//	if (vehicle != null) {
//			vehicle.setHistories(vehicle.getHistories().stream()
//					.sorted(Comparator.comparing(VehicleHistory::getRecordDate).reversed())
//					.collect(Collectors.toCollection(TreeSet::new)));
//
//			vehicle.setReviews(
//					vehicle.getReviews().stream().sorted(Comparator.comparing(CustomerReview::getReviewDate).reversed())
//							.collect(Collectors.toCollection(TreeSet::new)));
//		}
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
		return repo.findByHotDealTrueOrderByPriceAsc();
	}

	public void addVehicle(Vehicle vehicle) {
		repo.save(vehicle);
	}

	public void addReview(int vehicleId, CustomerReview review) {
		Vehicle vehicle = repo.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle not found"));
		review.setVehicle(vehicle);
		vehicle.getReviews().add(review);
		reviewRepo.save(review);
	}

	public void addHistory(int vehicleId, VehicleHistory history) {
		Vehicle vehicle = repo.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle not found"));
		history.setVehicle(vehicle);
		historyRepo.save(history);
	}

	public Vehicle updateVehicle(int id, Map<String, Object> updates) {
		Vehicle vehicle =  repo.findById(id).orElse(null);
		if (vehicle == null) return null;

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Vehicle.class, key);
			if (field != null) {
				field.setAccessible(true);
				try {
					if (field.getType().equals(BigDecimal.class) && value instanceof Number) {
						value = BigDecimal.valueOf(((Number) value).doubleValue());
					}
					ReflectionUtils.setField(field, vehicle, value);
				} catch (Exception e) {
					throw new RuntimeException("Could not update field: " + key, e);
				}
			}
		});
			return repo.save(vehicle);
	}

	public void deleteVehicle(int id) {
		repo.deleteById(id);
	}

	// Loan calculation
	public BigDecimal calculateMonthlyPayment(double vehiclePrice, double downPayment, double interestRate, double loanDuration) {
		double P = vehiclePrice - downPayment; // principal
		double r = (interestRate / 100) / 12; // monthly interest rate decimal
		if (interestRate == 0) return BigDecimal.valueOf(P / loanDuration).setScale(2, RoundingMode.HALF_UP);
		BigDecimal monthlyPayment = BigDecimal.valueOf((P * r) / (1 - Math.pow(1 + r, -loanDuration)));
		return monthlyPayment.setScale(2, RoundingMode.HALF_UP);
	}

	// Find a similar vehicle to compare with the given one, similarity based on condition (required), shape (optional) and year (optional)
	public VehicleProjection getSimilarVehicle(Vehicle givenVehicle) {
		String condition = givenVehicle.getVehicleCondition();
		String shape = givenVehicle.getShape();
		int year = givenVehicle.getModelYear();
		int id = givenVehicle.getId();

		// Start with all vehicles of the same condition
		List<VehicleProjection> sameConditionVehicles = repo.findByVehicleCondition(condition);

		// Filter out the given vehicle from the list
		List<VehicleProjection> potentialMatches = sameConditionVehicles.stream()
				.filter(v -> v.getId() != id)
				.toList();

		// First, try to find vehicles of the same condition, shape and year
		Optional<VehicleProjection> bestMatch = potentialMatches.stream()
				.filter(v -> v.getShape().equals(shape) && v.getModelYear() == year)
				.findFirst();

		// If there are no matches, look for vehicles of the same condition and shape
		if (bestMatch.isEmpty()) {
			bestMatch = potentialMatches.stream()
					.filter(v -> v.getShape().equals(shape))
					.findFirst();
		}

		// If no matches, look for vehicles of the same condition and year
		if (bestMatch.isEmpty()) {
			bestMatch = potentialMatches.stream()
					.filter(v -> v.getModelYear() == year)
					.findFirst();
		}

		// If no matches, select any vehicle of the same condition
		if (bestMatch.isEmpty()) {
			bestMatch = potentialMatches.stream()
					.findFirst();
		}

		// Return the most similar vehicle found (can be null only if no other vehicles of the same condition exist in the catalog)
        return bestMatch.orElse(null);
    }

}
