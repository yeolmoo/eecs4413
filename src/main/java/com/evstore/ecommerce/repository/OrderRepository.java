package com.evstore.ecommerce.repository;

import com.evstore.ecommerce.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer>  {
    List<PurchaseOrder> findByUserUsernameOrderByOrderDateDesc(String username);
}
