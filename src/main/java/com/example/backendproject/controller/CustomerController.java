package com.example.backendproject.controller;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Customers")
public class CustomerController {



    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/all")
    public List<CustomerDtoDetailed> allCustomersDetailed(){
        return customerService.getAllCustomersDetailed();
    }

    @GetMapping("/allmini")
    public List<CustomerDtoMini> allCustomersMini(){
        return customerService.getAllCustomersMini();
    }





}
