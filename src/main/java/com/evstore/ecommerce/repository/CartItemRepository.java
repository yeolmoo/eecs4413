package com.evstore.ecommerce.repository;

import com.evstore.ecommerce.model.Cart;
import com.evstore.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
