package com.example.backendproject.controller;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Bookings")
public class BookingController {


    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping("/allBookings")
    public List<BookingDtoDetailed> getAllBookings() {
        return bookingService.allBookingList();
    }

}
