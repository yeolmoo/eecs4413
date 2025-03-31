package com.evstore.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evstore.ecommerce.model.PurchaseOrder;

@Repository
public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    List<PurchaseOrder> findByUserUsernameOrderByOrderDateDesc(String username); 
}
