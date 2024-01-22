package com.example.springmongocart.dto;

import com.example.springmongocart.model.Cart;

import java.util.Collection;

public record CartDTO(String id, String customerId, Collection<CartItemDTO> cartItems) {

    public static CartDTO fromCart(Cart cart){
        return new CartDTO(
                cart.getId(),
                cart.getCustomer().getId(),
                cart.getItems().stream().map(e -> new CartItemDTO(
                        e.getId(),
                        new ProductDTO(
                                e.getProduct().getId(),
                                e.getProduct().getName(),
                                e.getProduct().getPrice()
                        ),
                        e.getQuantity()
                )).toList()
        );
    }
}
