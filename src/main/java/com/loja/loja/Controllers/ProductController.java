package com.loja.loja.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loja.loja.Services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<String> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Integer stockQuantity,
            @RequestParam("sizeId") List<Long> sizeId, 
            @RequestParam("colorId") List<Long> colorId,
            @RequestParam String gender,
            @RequestParam String brand,
            @RequestParam String imageUrl, // Imagem principal
            @RequestParam String category,
            @RequestParam List<String> imageUrls) {
                return productService.createProduct(name, description, price, stockQuantity, sizeId, colorId, gender, brand, imageUrl, category, imageUrls);


    }
}