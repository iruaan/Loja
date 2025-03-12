package com.loja.usecs.Services;

import org.springframework.stereotype.Service;

import com.loja.usecs.Model.Cart.CartItem;
import com.loja.usecs.Model.Cart.CartItemDTO;

@Service
public class CartService {

    public CartItemDTO toDTO(CartItem cartItem) {
        return new CartItemDTO(cartItem.getId(),
                cartItem.getQuantity(), cartItem.getSelectedColor(), cartItem.getSelectedSize());
    }

}
