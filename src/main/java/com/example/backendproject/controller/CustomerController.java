package com.example.backendproject.controller;


import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Bookings")
public class CustomerController {


    @Autowired
    private CustomerService customerService;





}
