package com.loja.usecs.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loja.usecs.Model.Product;
import com.loja.usecs.Services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Recebe todos os parâmetros do produto
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
            @RequestParam String imageUrl, // Link da imagem
            @RequestParam String category
    ) {
        // Cria o produto com os parâmetros recebidos
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setSize(size);
        product.setColor(color);
        product.setGender(gender);
        product.setBrand(brand);
        product.setImageUrl(imageUrl); // A URL da imagem
        product.setCategory(category);

        // Chama o serviço para salvar no banco de dados
        productService.createProduct(product);

        return ResponseEntity.ok("Produto criado com sucesso!");
    }
}
