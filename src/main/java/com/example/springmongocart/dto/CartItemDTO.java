package com.example.springmongocart.dto;

public record CartItemDTO(String id, ProductDTO product, int quantity) {
}
