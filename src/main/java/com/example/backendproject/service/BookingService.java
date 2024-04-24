package com.example.backendproject.service;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.models.Booking;
import com.example.backendproject.repo.BookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private BookingRepo bookingRepo;

  /*  public BookingDtoDetailed bookingDtoDetailed(Booking booking){
        return BookingDtoDetailed.builder().id(booking.getId()).checkInDate(booking.getCheckInDate()).checkOutDate(booking.getCheckOutDate())
                .customerDtoMini(new CustomerDtoMini().getEmail()).build();
    }

   */


}
