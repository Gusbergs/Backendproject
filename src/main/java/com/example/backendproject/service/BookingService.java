package com.example.backendproject.service;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.dto.RoomDtoMini;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookingService {



    final private RoomRepo roomRepo;

    final private BookingRepo bookingRepo;

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);





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

    public List<Booking> findAllBookings() {
        return bookingRepo.findAll();
    }
    @Transactional
    public void deleteBookingById(Long id) {
        try {
            if (bookingRepo.existsById(id)) {
                bookingRepo.deleteById(id);
                logger.info("Booking with ID {} has been deleted successfully.", id);
            } else {
                logger.warn("Booking with ID {} does not exist.", id);
            }
        } catch (Exception e) {
            logger.error("An error occurred while deleting booking with ID {}: {}", id, e.getMessage());

            throw new RuntimeException("Failed to delete booking with ID " + id, e);
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
