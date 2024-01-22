package com.example.springmongocart.service;

import com.example.springmongocart.dto.CartDTO;
import com.example.springmongocart.exception.NotFoundException;
import com.example.springmongocart.model.Cart;
import com.example.springmongocart.model.CartItem;
import com.example.springmongocart.model.Customer;
import com.example.springmongocart.model.Product;
import com.example.springmongocart.repository.CartItemRepository;
import com.example.springmongocart.repository.CartRepository;
import com.example.springmongocart.repository.CustomerRepository;
import com.example.springmongocart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private  final CartItemRepository cartItemRepository;

    private Customer ensureCustomerExists(String customerId) throws NotFoundException {
        return customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not Found"));
    }

    private Product ensureProductExists(String productId) throws NotFoundException {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not Found"));
    }


    private Cart createCart(Customer customer) {
        final var newCart = Cart.builder()
                .items(new ArrayList<>())
                .customer(customer).build();
        cartRepository.save(newCart);

        return newCart;
    }

    public CartDTO updateCartItemQuantity(String customerId, String productId, int quantity) throws NotFoundException {
        final var customer = ensureCustomerExists(customerId);
        final var cart = cartRepository.findDistinctByCustomerId(customerId).orElseGet( () -> createCart(customer));

        var index = -1;
        var cartItem = CartItem.builder().build();

        for (int i = 0; i < cart.getItems().size(); i++) {
            final var currItem = cart.getItems().get(i);

            if(currItem.getProduct().getId().equals(productId)){
                cartItem = currItem;
                index = i;
                break;
            }
        }

        cartItem.setQuantity(quantity);

        if(quantity <= 0){
            cart.remove(cartItem);
            if(cartItem.getId() != null){
                cartItemRepository.deleteById(cartItem.getId());
            }
            cartRepository.save(cart);
            return CartDTO.fromCart(cart);
        }

        if(cartItem.getProduct() == null){
            cartItem.setProduct(ensureProductExists(productId));
        }

        cartItem = cartItemRepository.save(cartItem);

        if(index == -1){
            cart.addItem(cartItem);
        }
        else{
            cart.setItemAtIndex(index, cartItem);
        }

        cartRepository.save(cart);

        return CartDTO.fromCart(cart);
    }

    public CartDTO getOrCreateCart(String customerId) throws NotFoundException {
        final var customer = ensureCustomerExists(customerId);
        var cart = cartRepository.findDistinctByCustomerId(customerId).orElseGet(() -> createCart(customer));

        return CartDTO.fromCart(cart);
    }
}
