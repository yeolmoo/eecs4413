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

    List<VehicleProjection> findAllBy();

    // Getting additional details from tables with foreign key
    @EntityGraph(attributePaths = {"histories", "reviews"})
    Optional<Vehicle> findWithDetailsById(int id);

    @Query("SELECT DISTINCT v.brand FROM Vehicle v")
    List<String> findDistinctBrands();

    @Query("SELECT DISTINCT v.shape FROM Vehicle v")
    List<String> findDistinctShapes();

    @Query("SELECT DISTINCT v.modelYear FROM Vehicle v")
    List<String> findDistinctModelYears();

    @Query("SELECT v FROM Vehicle v WHERE " +
            "(:brand IS NULL OR v.brand = :brand) AND " +
            "(:shape IS NULL OR v.shape = :shape) AND " +
            "(:modelYear IS NULL OR v.modelYear = :modelYear) AND " +
            "(:havingHistory IS NULL OR v.havingHistory = :havingHistory) AND " +
            "(:vehicleCondition IS NULL OR v.vehicleCondition = :vehicleCondition)")
    List<VehicleProjection> findByFilters(@Param("brand") String brand,
                                @Param("shape") String shape,
                                @Param("modelYear") Integer modelYear,
                                @Param("havingHistory") Boolean havingHistory,
                                @Param("vehicleCondition") String vehicleCondition);

    List<VehicleProjection> findByHotDealTrueOrderByPriceAsc();
}
