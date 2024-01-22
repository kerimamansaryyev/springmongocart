package com.example.springmongocart.request;

public record CartUpdateRequest(String customerId, String productId, int quantity) {
}
