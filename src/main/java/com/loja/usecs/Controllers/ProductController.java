package com.loja.usecs.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loja.usecs.Model.Color;
import com.loja.usecs.Model.Product;
import com.loja.usecs.Model.ProductImage;
import com.loja.usecs.Model.Size;
import com.loja.usecs.Repository.ColorRepository;
import com.loja.usecs.Repository.SizeRepository;
import com.loja.usecs.Services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Integer stockQuantity,
            @RequestParam Long sizeId, // ID do tamanho
            @RequestParam Long colorId, // ID da cor
            @RequestParam String gender,
            @RequestParam String brand,
            @RequestParam String imageUrl, // Imagem principal
            @RequestParam String category,
            @RequestParam List<String> imageUrls) {

        Size size = sizeRepository.findById(sizeId)
                .orElseThrow(() -> new RuntimeException("Tamanho não encontrado"));

        // Buscar o objeto Color pelo ID
        Color color = colorRepository.findById(colorId)
                .orElseThrow(() -> new RuntimeException("Cor não encontrada"));

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

        List<ProductImage> images = new ArrayList<>();
        for (String url : imageUrls) {
            ProductImage productImage = new ProductImage(url.trim(), product); // Remover espaços em branco
            images.add(productImage);
        }
        product.setImages(images); // Definir as imagens secundárias no produto

        productService.createProduct(product);

        return ResponseEntity.ok("Produto criado com sucesso!");
    }
}