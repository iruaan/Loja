package com.loja.loja.Model.Cart;

public class CartItemDTO {
    private Long id;
    private String productName;
    private double productPrice;
    private int quantity;

    // Construtor, getters e setters
    public CartItemDTO(Long id, String productName, double productPrice, int quantity) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getters e Setters
}

