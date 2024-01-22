package com.example.springmongocart.repository;

import com.example.springmongocart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findDistinctByCustomerId(String customerId);
}
