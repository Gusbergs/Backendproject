package com.example.backendproject.service;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.dto.RoomDtoMini;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;

    private final CustomerRepo customerRepo;

    public BookingService(BookingRepo bookingRepo, CustomerRepo customerRepo) {
        this.bookingRepo = bookingRepo;
        this.customerRepo = customerRepo;
    }
    public BookingDtoDetailed bookingDtoDetailed(Booking booking) {
       return BookingDtoDetailed.builder().id(booking.getId()).checkInDate(booking.getCheckInDate()).checkOutDate(booking.getCheckOutDate())
              .customerDtoMini(new CustomerDtoMini(booking.getCustomer().getId()
                      , booking.getCustomer().getName()
                      , booking.getCustomer().getEmail()))
              .roomDtoMini(new RoomDtoMini(booking.getRoom().getId()
                       ,booking.getRoom().getRoomNumber()
                       , booking.getRoom().isDoubleRoom()
                       , booking.getRoom().getExtraBed())).build();
    }

    public List<BookingDtoDetailed> allBookingList() {
        return bookingRepo.findAll().stream().map(booking -> bookingDtoDetailed(booking)).toList();
    }

    public String addNewBooking(BookingDtoDetailed booking) {
        Customer newKund = customerRepo.findById(booking.getCustomerDtoMini().getId()).get();
        return "Kund " + newKund.getName() + " sparas";
    }



}
