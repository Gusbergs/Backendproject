package com.example.backendproject.controller;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.models.Booking;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {



    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping("/all")
    public List<BookingDtoDetailed> allBookings(){
        return bookingService.getAllBookingsDetailed();
    }

    @GetMapping("/allmini")
    public List<BookingDtoMini> allBookingsMini(){
        return bookingService.getAllBookingsMini();
    }



}
