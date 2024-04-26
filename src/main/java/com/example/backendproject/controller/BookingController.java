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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {



    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping("/all")
    public @ResponseBody List<BookingDtoDetailed> allBookings(){
        return bookingService.getAllBookingsDetailed();
    }

    @GetMapping("/allmini")
    public @ResponseBody List<BookingDtoMini> allBookingsMini(){
        return bookingService.getAllBookingsMini();
    }
    @RequestMapping("/Book-A-Room")
    public String booking(){
        return "book-room.html";
    }


}
