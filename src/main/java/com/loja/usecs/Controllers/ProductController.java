package com.loja.usecs.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loja.usecs.Model.Product;
import com.loja.usecs.Model.ProductImage;
import com.loja.usecs.Services.ProductService;

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
            @RequestParam String size,
            @RequestParam String color,
            @RequestParam String gender,
            @RequestParam String brand,
            @RequestParam String imageUrl, // Imagem principal
            @RequestParam String category,
            @RequestParam List<ProductImage> image
    ) {
    
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setSize(size);
        product.setColor(color);
        product.setGender(gender);
        product.setBrand(brand);
        product.setImageUrl(imageUrl);
        product.setCategory(category);
        product.setImages(image);

    
    
        productService.createProduct(product);
    
        return ResponseEntity.ok("Produto criado com sucesso!");
    }
}