package com.example.testspringmongoapp.dto;

public record CartItemDTO(String id, ProductDTO product, int quantity) {
}
