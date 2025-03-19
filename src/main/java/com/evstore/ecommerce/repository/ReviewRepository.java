package com.evstore.ecommerce.repository;

import com.evstore.ecommerce.model.CustomerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<CustomerReview, Integer> {
}
