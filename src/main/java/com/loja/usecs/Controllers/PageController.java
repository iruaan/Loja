package com.loja.usecs.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.loja.usecs.Model.Product;
import com.loja.usecs.Repository.ProductRepository;



@Controller
public class PageController {

    @Autowired
    private ProductRepository productRepository;

    
    @GetMapping("/home")
    public String home(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products); // aqui é products em inglês mesmo
        return "Home"; // isso chama seu home.html
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
}
