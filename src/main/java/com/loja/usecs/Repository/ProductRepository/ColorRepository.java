package com.loja.usecs.Repository.ProductRepository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.usecs.Model.Products.Color;




@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
Color findByName(String selectedColor);
    
}
