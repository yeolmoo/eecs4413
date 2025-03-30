package com.evstore.ecommerce.repository;

import com.evstore.ecommerce.model.VehicleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<VehicleHistory, Integer> {
}
