package com.example.springmongocart.controller;

import com.example.springmongocart.exception.NotFoundException;
import com.example.springmongocart.request.CartUpdateRequest;
import com.example.springmongocart.response.ErrorResponse;
import com.example.springmongocart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartService cartService;

    @RequestMapping(value = "/cart/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<?> readCart(@PathVariable String customerId) {
        try {
            return new ResponseEntity<>(cartService.getOrCreateCart(customerId), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cart/", method = RequestMethod.POST)
    public ResponseEntity<?> updateCartItem(@RequestBody CartUpdateRequest request) {
        try {
            return new ResponseEntity<>(
                    cartService.updateCartItemQuantity(request.customerId(), request.productId(), request.quantity()),
                    HttpStatus.OK
            );
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
