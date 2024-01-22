package com.example.testspringmongoapp;
import com.example.testspringmongoapp.dto.CartDTO;
import com.example.testspringmongoapp.dto.CustomerDTO;
import com.example.testspringmongoapp.dto.ProductDTO;
import com.example.testspringmongoapp.request.CartUpdateRequest;
import com.example.testspringmongoapp.request.CustomerCreateRequest;
import com.example.testspringmongoapp.request.ProductCreateRequest;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class Main {
    private static final RestOperations restTemplate = new RestTemplate();

    public static void main(String[] args) {
        callRestServer();
    }

    public static void callRestServer() {


        String serverUrl = "http://localhost:8080";

        CustomerDTO customerDTO = restTemplate.postForObject(
                serverUrl+"/customer", new CustomerCreateRequest("Kevin"),
                 CustomerDTO.class
        );

        ProductDTO productDTO = restTemplate.postForObject(
                serverUrl+"/product", new ProductCreateRequest("iPhone", 1200),
                ProductDTO.class
        );

        CartDTO cartDTO = restTemplate.postForObject(
                serverUrl+"/cart/", new CartUpdateRequest(
                        customerDTO.id(),
                        productDTO.id(),
                        123
                ),
                CartDTO.class
        );

        for(final var p: cartDTO.cartItems()){
            System.out.println(p.product().name());
            System.out.println(p.quantity());
        }
    }
}
