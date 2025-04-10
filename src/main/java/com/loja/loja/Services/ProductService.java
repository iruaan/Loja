package com.loja.loja.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.loja.loja.Model.Products.Color;
import com.loja.loja.Model.Products.Product;
import com.loja.loja.Model.Products.ProductImage;
import com.loja.loja.Model.Products.Size;
import com.loja.loja.Repository.ProductRepository.ColorRepository;
import com.loja.loja.Repository.ProductRepository.ProductRepository;
import com.loja.loja.Repository.ProductRepository.SizeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

        @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;



    // Criar um novo produto
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

        List<Size> size = sizeRepository.findAllById(sizeId);
                if (sizeId == null) {
                    throw new RuntimeException("Tamanho não encontrado");
                }

        // Buscar o objeto Color pelo ID
        List<Color> color = colorRepository.findAllById(colorId);
        if (colorId == null) {
            throw new RuntimeException("Cor não encontrada");
        }

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setSizes(size);
        product.setColors(color);
        product.setGender(gender);
        product.setBrand(brand);
        product.setImageUrl(imageUrl);
        product.setCategory(category);
        product.setCreatedAt(java.time.LocalDateTime.now());
        product.setUpdatedAt(java.time.LocalDateTime.now());

        List<ProductImage> images = new ArrayList<>();
        for (String url : imageUrls) {
            ProductImage productImage = new ProductImage(url.trim(), product); // Remover espaços em branco
            images.add(productImage);
        }
        product.setImages(images); // Definir as imagens secundárias no produto

        productRepository.save(product);

        return ResponseEntity.ok("Produto criado com sucesso!");
    }

    // Listar todos produtos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Buscar por ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Atualizar um produto existente
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
                
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setSizes(productDetails.getSizes());
        product.setColors(productDetails.getColors());
        product.setGender(productDetails.getGender());
        product.setBrand(productDetails.getBrand());
        product.setImageUrl(productDetails.getImageUrl());
        product.setActive(productDetails.getActive());
        product.setCategory(productDetails.getCategory());
        product.setUpdatedAt(java.time.LocalDateTime.now());

        return productRepository.save(product);
    }

    // Deletar produto
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }




}
