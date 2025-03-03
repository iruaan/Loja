package com.loja.usecs.Services;

import com.loja.usecs.Model.Product;
import com.loja.usecs.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Criar um novo produto
    public Product createProduct(Product product) {
        product.setCreatedAt(java.time.LocalDateTime.now());
        product.setUpdatedAt(java.time.LocalDateTime.now());
        return productRepository.save(product);
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
        product.setSize(productDetails.getSize());
        product.setColor(productDetails.getColor());
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
