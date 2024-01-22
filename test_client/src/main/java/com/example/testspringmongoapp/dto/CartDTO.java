package com.example.testspringmongoapp.dto;

import java.util.Collection;

public record CartDTO(String id, String customerId, Collection<CartItemDTO> cartItems) {
}
