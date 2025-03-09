package com.loja.usecs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.usecs.Model.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    
}
