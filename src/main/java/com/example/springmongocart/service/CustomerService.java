package com.example.springmongocart.service;

import com.example.springmongocart.dto.CustomerDTO;
import com.example.springmongocart.exception.NotFoundException;
import com.example.springmongocart.model.Customer;
import com.example.springmongocart.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerDTO createCustomer(String name) {
        final var customer = customerRepository.save(
                Customer.builder().name(name).build()
        );

        return new CustomerDTO(
                customer.getId(),
                customer.getName()
        );
    }
}
