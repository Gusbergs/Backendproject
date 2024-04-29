package com.example.backendproject.service;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.dto.RoomDtoMini;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {



   final private BookingRepo bookingRepo;



    public BookingDtoDetailed bookingDtoDetailed(Booking booking) {
        return BookingDtoDetailed.builder().id(booking.getId()).checkInDate(booking.getCheckInDate()).checkOutDate(booking.getCheckOutDate())
                .customerDtoMini(new CustomerDtoMini(booking.getCustomer().getId()
                        , booking.getCustomer().getName()
                        , booking.getCustomer().getEmail())).
                roomDtoMini(new RoomDtoMini(booking.getRoom().getId()
                ,booking.getRoom().getRoomNumber()
                ,booking.getRoom().isDoubleRoom()
                ,booking.getRoom().getExtraBed())).build();
    }

    public BookingDtoMini bookingtoDtoMini(Booking booking) {
        return BookingDtoMini.builder().id(booking.getId()).checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate()).build();
    }


    public List<BookingDtoDetailed> getAllBookingsDetailed(){
        return bookingRepo.findAll().stream().map(booking -> bookingDtoDetailed(booking)).toList();
    }

    public List<BookingDtoMini> getAllBookingsMini(){
        return bookingRepo.findAll().stream().map(booking -> bookingtoDtoMini(booking)).toList();
    }

    public Booking BookingDtoToBooking(BookingDtoDetailed bookingDtoDetailed, Customer customer, Room room) {
        return Booking.builder()
                .id(bookingDtoDetailed.getId())
                .checkInDate(bookingDtoDetailed.getCheckInDate())
                .checkOutDate(bookingDtoDetailed.getCheckOutDate())
                .room(room)
                .customer(customer)
                .build();
    }

    /*public void deleteBooking(BookingDtoDetailed booking) {
        bookingRepo.deleteById(booking.getId());
    }*/

}
