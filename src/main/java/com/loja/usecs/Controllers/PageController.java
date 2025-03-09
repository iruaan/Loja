package com.loja.usecs.Controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.loja.usecs.Model.Product;
import com.loja.usecs.Repository.ColorRepository;
import com.loja.usecs.Repository.ProductRepository;
import com.loja.usecs.Repository.SizeRepository;



@Controller
public class PageController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private SizeRepository sizeRepository;  

    @Autowired
    private ColorRepository colorRepository;  


    
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
    public String showProductForm(Model model) {

        model.addAttribute("sizes", sizeRepository.findAll());  // Envia todos os tamanhos
        model.addAttribute("colors", colorRepository.findAll()); // Envia todas as cores
        model.addAttribute("product", new Product()); // Envia o objeto vazio do produto para o formulário
        return "product";
    }

    @GetMapping("/products/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
    
        if (optionalProduct.isEmpty()) {
            return "redirect:/products";
        }
    
        Product product = optionalProduct.get();  // Atribuindo o produto
        model.addAttribute("product", product);  // Passando o produto para o template
        model.addAttribute("sizes", product.getSize());
        model.addAttribute("colors", product.getColor());
    
        return "product-detail";
    }
    
    

}
