package com.loja.usecs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.usecs.Model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    
}
