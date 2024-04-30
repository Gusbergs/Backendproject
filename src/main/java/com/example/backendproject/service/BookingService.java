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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookingService {


    final private BookingRepo bookingRepo;
    final private RoomRepo roomRepo;


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
    public void deleteBookingById(Long id) { // körs men deletar ej
        if (bookingRepo.existsById(id)) {
            System.out.println("booking does exist");
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

    @Transactional
    public boolean createBooking(Room room, LocalDate startDate, LocalDate endDate) {
        // Kontrollerar om det finns några överlappande bokningar för det angivna rummet och datumintervallet
        List<Booking> existingBookings = bookingRepo.findBookingsByDateRangeAndRoom(room.getId(), startDate, endDate);
        if (!existingBookings.isEmpty()) {
            return false;  // Det finns en överlappande bokning, returnera false
        }

        // Skapar och sparar en ny bokning
        Booking newBooking = new Booking();
        newBooking.setRoom(new Room(room.getRoomNumber(), room.isDoubleRoom(), room.getExtraBed()));  // Antag att Room-konstruktören bara ställer in ID
        newBooking.setCheckInDate(startDate);
        newBooking.setCheckOutDate(endDate);
        bookingRepo.save(newBooking);
        return true;  // Bokningen lyckades, returnera true
    }
}
