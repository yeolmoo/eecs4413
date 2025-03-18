package com.evstore.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evstore.ecommerce.model.Cart;
import com.evstore.ecommerce.user.User;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
  
    Optional<Cart> findByUser(User user);
}
