package com.example.springmongocart.service;

import com.example.springmongocart.dto.ProductDTO;
import com.example.springmongocart.model.Product;
import com.example.springmongocart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private  final ProductRepository productRepository;


    public ProductDTO createProduct(double price, String name){
        final var product = productRepository.save(
                Product.builder().name(name).price(price).build()
        );
        return  new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
