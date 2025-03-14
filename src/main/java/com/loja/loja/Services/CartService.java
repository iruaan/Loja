package com.loja.loja.Services;

import org.springframework.stereotype.Service;

import com.loja.loja.Model.Cart.CartItem;
import com.loja.loja.Model.Cart.CartItemDTO;

@Service
public class CartService {

    public CartItemDTO toDTO(CartItem cartItem) {
        return new CartItemDTO(cartItem.getId(),
                cartItem.getQuantity(), cartItem.getSelectedColor(), cartItem.getSelectedSize());
    }

}
