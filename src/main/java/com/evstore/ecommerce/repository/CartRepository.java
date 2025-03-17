package com.evstore.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evstore.ecommerce.model.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
  
}
