package com.evstore.ecommerce.repository;

import com.evstore.ecommerce.model.OrderItem;
import com.evstore.ecommerce.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
