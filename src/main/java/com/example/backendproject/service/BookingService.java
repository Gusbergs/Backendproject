package com.example.backendproject.service;


import com.example.backendproject.dto.*;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookingService {


    @Autowired
    private BookingRepo bookingRepo;

    final private RoomRepo roomRepo;
    private final CustomerRepo customerRepo;


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

    public boolean findCrossedTime(LocalDate start, LocalDate stop, RoomDtoDetailed room) {
        boolean isAvaliable = false;

        if (room.getBookingDtoDetailedList().isEmpty()) {
            isAvaliable = true;
        } else {
            for (BookingDtoDetailed books : room.getBookingDtoDetailedList()) {
                System.out.println("Check in: " + books.getCheckInDate() + "\n" +
                        "Check out: " + books.getCheckOutDate());
                if (books.getCheckInDate().isAfter(start) && books.getCheckInDate().isAfter(stop)) {
                    isAvaliable = true;
                } else if (books.getCheckOutDate().isBefore(start) && books.getCheckOutDate().isBefore(stop)) {
                    isAvaliable = true;
                } else {
                    isAvaliable = false;
                    break;
                }
            }
        }

        return isAvaliable;
    }

    public BookingDtoDetailed getBookingById2(Long id) {
        Booking booking = bookingRepo.getReferenceById(id);
        return bookingDtoDetailed(booking);
    }


    public List<BookingDtoDetailed> getAllBookingsDetailed(){
        return bookingRepo.findAll().stream().map(booking -> bookingDtoDetailed(booking)).toList();
    }

    public List<BookingDtoMini> getAllBookingsMini(){
        return bookingRepo.findAll().stream().map(booking -> bookingtoDtoMini(booking)).toList();
    }

    public List<Booking> findAllBookings() {
        return bookingRepo.findAll();
    }
    @Transactional
    public void deleteBookingById(Long id) { // körs men deletar ej
        if (bookingRepo.existsById(id)) {

            bookingRepo.deleteById(id);

        } else {
            System.out.println("booking does not exist!");
        }
    }

    public Optional<Booking> findBookingById(Long id) {
        return bookingRepo.findById(id);
    }



    public void updateBookingById(Long id, LocalDate newCheckInDate, LocalDate newCheckOutDate, Long roomId) {
        bookingRepo.findById(id).ifPresent(booking -> {

            Room room = roomRepo.findById(roomId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid room ID: " + roomId));

            booking.setCheckInDate(newCheckInDate);
            booking.setCheckOutDate(newCheckOutDate);
            booking.setRoom(room);

            bookingRepo.save(booking);
        });
    }

}
