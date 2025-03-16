package com.evstore.ecommerce.repository;

import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.model.VehicleProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Vehicle, Integer> {

	List<VehicleProjection> findAllByOrderById();

	// Getting additional details from tables with foreign key
	@Query("SELECT v FROM Vehicle v LEFT JOIN FETCH v.histories LEFT JOIN FETCH v.reviews WHERE v.id = :id")
	Optional<Vehicle> findWithDetailsById(@Param("id") int id);

	@Query("SELECT DISTINCT v.brand FROM Vehicle v")
	List<String> findDistinctBrands();

	@Query("SELECT DISTINCT v.shape FROM Vehicle v")
	List<String> findDistinctShapes();

	@Query("SELECT DISTINCT v.modelYear FROM Vehicle v")
	List<String> findDistinctModelYears();

	@Query("SELECT v FROM Vehicle v WHERE " + "(COALESCE(:brand, v.brand) = v.brand) AND "
			+ "(COALESCE(:shape, v.shape) = v.shape) AND " + "(COALESCE(:modelYear, v.modelYear) = v.modelYear) AND "
			+ "(COALESCE(:havingHistory, v.havingHistory) = v.havingHistory) AND "
			+ "(COALESCE(:vehicleCondition, v.vehicleCondition) = v.vehicleCondition)")
	List<VehicleProjection> findByFilters(@Param("brand") String brand, @Param("shape") String shape,
			@Param("modelYear") Integer modelYear, @Param("havingHistory") Boolean havingHistory,
			@Param("vehicleCondition") String vehicleCondition);

	@Query("SELECT v FROM Vehicle v WHERE v.hotDeal = TRUE ORDER BY v.price ASC")
	List<VehicleProjection> findHotDeals();
}
