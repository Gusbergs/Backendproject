package com.example.backendproject.controller;


import com.example.backendproject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Bookings")
public class BookingController {


    @Autowired
    private BookingService bookingService;




}
