package com.loja.usecs.Repository.ProductRepository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.usecs.Model.Products.Size;


@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    Size findByName(String selectedSize);
    
}
