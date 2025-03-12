package com.loja.usecs.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.loja.usecs.Model.Products.Color;
import com.loja.usecs.Model.Products.Product;
import com.loja.usecs.Model.Products.ProductImage;
import com.loja.usecs.Model.Products.Size;
import com.loja.usecs.Repository.ProductRepository.ColorRepository;
import com.loja.usecs.Repository.ProductRepository.SizeRepository;
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