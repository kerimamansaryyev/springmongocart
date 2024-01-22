package com.example.testspringmongoapp.request;

public record CartUpdateRequest(String customerId, String productId, int quantity) {
}
