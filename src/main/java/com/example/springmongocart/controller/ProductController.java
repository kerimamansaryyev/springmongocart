package com.example.springmongocart.controller;

import com.example.springmongocart.dto.ProductDTO;
import com.example.springmongocart.request.ProductCreateRequest;
import com.example.springmongocart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private  final ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateRequest request){
        return new ResponseEntity<>(
                productService.createProduct(
                        request.price(),
                        request.name()
                ),
                HttpStatus.OK
        );
    }
}
