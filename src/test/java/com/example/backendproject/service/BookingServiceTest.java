package com.example.backendproject.service;

import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private CustomerRepo customerRepo;

    private Customer customer;
    private Room room;
    private Booking booking;

    @BeforeEach
    public void setUp() {
        // Clear all data from the repositories
        bookingRepo.deleteAll();
        roomRepo.deleteAll();
        customerRepo.deleteAll();

        // Set up initial data
        customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customerRepo.save(customer);

        room = new Room();
        room.setRoomNumber(101);
        room.setDoubleRoom(true);
        room.setExtraBed(0);
        room.setPrice(100.0);
        roomRepo.save(room);

        booking = new Booking();
        booking.setCheckInDate(LocalDate.of(2024, 6, 1));
        booking.setCheckOutDate(LocalDate.of(2024, 6, 10));
        booking.setCustomer(customer);
        booking.setRoom(room);
        bookingRepo.save(booking);
    }

    @Test
    public void testGetBookingById() {
        BookingDtoDetailed bookingDto = bookingService.getBookingById2(booking.getId());
        assertThat(bookingDto).isNotNull();
        assertThat(bookingDto.getCustomerDtoMini().getName()).isEqualTo("John Doe");
    }

    @Test
    public void testFindAllBookings() {
        assertThat(bookingService.findAllBookings()).hasSize(1);
    }

    @Test
    public void testDeleteBookingById() {
        bookingService.deleteBookingById(booking.getId());
        Optional<Booking> deletedBooking = bookingRepo.findById(booking.getId());
        assertThat(deletedBooking).isEmpty();
    }

    @Test
    public void testUpdateBookingById() {
        LocalDate newCheckInDate = LocalDate.of(2024, 7, 1);
        LocalDate newCheckOutDate = LocalDate.of(2024, 7, 10);
        bookingService.updateBookingById(booking.getId(), newCheckInDate, newCheckOutDate, room.getId());
        Booking updatedBooking = bookingRepo.findById(booking.getId()).get();
        assertThat(updatedBooking.getCheckInDate()).isEqualTo(newCheckInDate);
        assertThat(updatedBooking.getCheckOutDate()).isEqualTo(newCheckOutDate);
    }
}
