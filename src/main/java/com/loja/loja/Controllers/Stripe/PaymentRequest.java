package com.loja.loja.Controllers.Stripe;

import java.util.List;

import com.loja.loja.Model.Cart.CartItemDTO;

public class PaymentRequest {
    private String paymentMethodId;
    private List<CartItemDTO> cartItems;

    // Getters e Setters
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}