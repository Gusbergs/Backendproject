package com.example.backendproject.controller;


import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Bookings")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @RequestMapping("/allCustomer")
    public @ResponseBody List<CustomerDtoDetailed> getAllCustomerList() {
        return customerService.getAllListDtoDetailed();
    }




}
