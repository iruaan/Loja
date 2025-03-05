package com.loja.usecs.Controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.loja.usecs.Model.Product;
import com.loja.usecs.Repository.ProductRepository;



@Controller
public class PageController {

    @Autowired
    private ProductRepository productRepository;

    
    @GetMapping("/home")
    public String home(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products); 
        return "Home"; 
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/product")
    public String producuts(){
        return "product";
    }

    @GetMapping("/products/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
    
        if (optionalProduct.isEmpty()) {
            // Redireciona para a página de produtos ou exibe uma página 404 personalizada
            return "redirect:/products";
        }
    
        Product product = optionalProduct.get();
        model.addAttribute("product", product);
    
        return "product-detail";
    }

}
