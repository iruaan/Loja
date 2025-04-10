package com.loja.loja.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loja.loja.Model.Cart.CartItem;
import com.loja.loja.Model.Cart.CartItemDTO;
import com.loja.loja.Model.Products.Color;
import com.loja.loja.Model.Products.Product;
import com.loja.loja.Model.Products.Size;
import com.loja.loja.Model.User.UserInfo;
import com.loja.loja.Repository.UserRepository;
import com.loja.loja.Repository.ProductRepository.CartRepository;
import com.loja.loja.Repository.ProductRepository.ColorRepository;
import com.loja.loja.Repository.ProductRepository.ProductRepository;
import com.loja.loja.Repository.ProductRepository.SizeRepository;

@Service
public class CartService {

        @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public String addToCart(
            @PathVariable Long productId,
            @RequestParam Integer quantity,
            @RequestParam String selectedColor,
            @RequestParam String selectedSize,
            RedirectAttributes redirectAttributes) {
        // Obtém a autenticação do usuário logado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Busca o usuário pelo email
        UserInfo user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        // Busca o produto pelo ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Busca a cor selecionada
        Color color = colorRepository.findByName(selectedColor);
        if (color == null) {
            throw new RuntimeException("Cor não encontrada");
        }

        // Busca o tamanho selecionado
        Size size = sizeRepository.findByName(selectedSize);
        if (size == null) {
            throw new RuntimeException("Tamanho não encontrado");
        }

        // Cria um novo item do carrinho
        CartItem cartItem = new CartItem();
        cartItem.setUser(user); // Associa o usuário ao item do carrinho
        cartItem.setProduct(product); // Associa o produto ao item do carrinho
        cartItem.setQuantity(quantity);
        cartItem.setSelectedColor(color.getName());
        cartItem.setSelectedSize(size.getName());

        // Salva o item no banco de dados
        cartRepository.save(cartItem);

        // Adiciona uma mensagem de sucesso
        redirectAttributes.addFlashAttribute("successMessage", "Produto adicionado ao carrinho com sucesso!");
              // Redireciona para a página de detalhes do produto
              return "redirect:/products/" + productId;
    }


    // Pega a quantidade e calcula o total
    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public double calculateTotal(List<CartItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getProductPrice() * item.getQuantity())
                .sum();
    }

    // Transforma em DTO
        public List<CartItemDTO> getCartItemDTOs() {
        List<CartItem> cartItems = cartRepository.findAll();
        List<CartItemDTO> dtos = new ArrayList<>();

        for (CartItem item : cartItems) {
            CartItemDTO dto = new CartItemDTO(
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity()
            );
            dtos.add(dto);
        }

        return dtos;
    }
    
}
