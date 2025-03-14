package com.loja.loja.Repository.ProductRepository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.loja.Model.Products.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
