package com.example.springmongocart.controller;

import com.example.springmongocart.request.CustomerCreateRequest;
import com.example.springmongocart.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private  final CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody CustomerCreateRequest body){
        return new ResponseEntity<>(customerService.createCustomer(body.name()), HttpStatus.OK);
    }
}
