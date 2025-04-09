package com.loja.loja.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.loja.loja.Model.Cart.CartItemDTO;
import com.loja.loja.Model.Cart.CartItem;
import com.loja.loja.Services.CartService;

@Controller
public class CartController {


    @Autowired
    CartService cartService;

    @PostMapping("/cart/add/{productId}")
    public String addToCart(
    @PathVariable Long productId,
    @RequestParam Integer quantity,
    @RequestParam String selectedColor,
    @RequestParam String selectedSize,
    RedirectAttributes redirectAttributes) { 
    return cartService.addToCart(productId, quantity, selectedColor, selectedSize, redirectAttributes);
   
    }

    @GetMapping("/cart")
    public String home(Model model) {

         List<CartItem> cartItems = cartService.getCartItems();
          double totalAmount = cartService.calculateTotal(cartItems);
                
        // Adiciona os itens e o valor total ao modelo
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);

        System.out.println(totalAmount);

        return "cart"; 

    }

    @GetMapping("/api/cart")
    @ResponseBody
    public List<CartItemDTO> getCartItems() {
        return cartService.getCartItemDTOs();
    }
    
}

    

