package com.evstore.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.evstore.ecommerce.model.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer>{
  
}

