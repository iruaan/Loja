package com.loja.loja.Repository.ProductRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.loja.Model.Products.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductId(Long productId);
}
