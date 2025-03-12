package com.loja.usecs.Repository.ProductRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.usecs.Model.Cart.CartItem;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    
}
