package com.loja.loja.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.loja.loja.Model.Cart.CartItem;
import com.loja.loja.Model.Products.Product;
import com.loja.loja.Repository.ProductRepository.CartRepository;
import com.loja.loja.Repository.ProductRepository.ColorRepository;
import com.loja.loja.Repository.ProductRepository.ProductRepository;
import com.loja.loja.Repository.ProductRepository.SizeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PageController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/home")
    public String home(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "Home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/checkout")
    public String showCheckout(Model model) {
        List<CartItem> cartItems = cartRepository.findAll();
        model.addAttribute("cartItems", cartItems);

        return "checkout";
    }

    @GetMapping("/product")
    public String showProductForm(Model model) {

        model.addAttribute("sizes", sizeRepository.findAll()); // Envia todos os tamanhos
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

        Product product = optionalProduct.get(); // Atribuindo o produto
        model.addAttribute("product", product); // Passando o produto para o template
        model.addAttribute("sizes", product.getSizes());
        model.addAttribute("colors", product.getColors());

        return "product-detail";
    }

    @DeleteMapping("/cart/remove/{itemId}")
    public ResponseEntity<Void> deleteCartItemById(@PathVariable Long itemId, Model model) {

        if (cartRepository.existsById(itemId)) {
            cartRepository.deleteById(itemId);
    
            return ResponseEntity.ok().build(); // Retorna apenas um "200 OK"
        }
        return ResponseEntity.notFound().build(); // Retorna "404 Not Found" se o item não existir
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }

}
