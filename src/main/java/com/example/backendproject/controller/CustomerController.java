package com.example.backendproject.controller;


import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Customer")
public class CustomerController {




    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @RequestMapping("/allCustomer")
    public List<CustomerDtoDetailed> getAllCustomer() {
        return customerService.getAllListDtoDetailed();
    }





}
