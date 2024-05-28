package com.example.backendproject.service;

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
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class DiscountServiceTest {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private CustomerRepo customerRepo;

    private String testEmail;
    private Long testBookingId;

   @BeforeEach
    public void setup() {
        testEmail = "customer@example.com";
        LocalDate checkInDate = LocalDate.of(2024, 5, 24);
        LocalDate checkOutDate = LocalDate.of(2024, 5, 27);

        Customer customer = new Customer(1L, "Kalle", testEmail);
        Room room = new Room(1, false, 0, 100);
        customerRepo.save(customer);
        roomRepo.save(room);
        Booking booking = new Booking(checkInDate, checkOutDate, room, customer);
        bookingRepo.save(booking);
        testBookingId = booking.getId();
    }

    @Test
    public void testGetDiscount_NoRecentBookings_ShortDuration_NoSundayToMonday() {
        testEmail = "customer@example30.com";
        LocalDate checkInDate = LocalDate.of(2024, 5, 22);
        LocalDate checkOutDate = LocalDate.of(2024, 5, 23);

        Customer customer = new Customer(3L, "Kalle", testEmail);
        Room room = new Room(1, false, 0, 100);
        customerRepo.save(customer);
        roomRepo.save(room);
        Booking booking = new Booking(checkInDate, checkOutDate, room, customer);
        bookingRepo.save(booking);

        double discount = discountService.getDiscount(customer.getEmail(), booking.getId());
        assertEquals(1.0, discount);
    }

    @Test
    public void testGetDiscount_RecentBookings_ShortDuration_NoSundayToMonday() {
        // Skapa 10 bokningar under senaste året

            long customerId = 1;

        for (int i = 0; i < 10; i++) {
            testEmail = "customer@example10.com";
            LocalDate checkInDate = LocalDate.of(2024, 5, 24);
            LocalDate checkOutDate = LocalDate.of(2024, 5, 26);

            Customer customer = new Customer(customerId, "Kalle", testEmail);
            Room room = new Room(1, false, 0, 100);
            customerRepo.save(customer);
            roomRepo.save(room);
            Booking booking = new Booking(checkInDate, checkOutDate, room, customer);
            booking.setCheckInDate(LocalDate.now().minusMonths(2 + i));
            booking.setCheckOutDate(LocalDate.now().minusMonths(2 + i).plusDays(1));
            bookingRepo.save(booking);
        }

        Booking booking = bookingRepo.findById(testBookingId).get();
        booking.setCheckInDate(LocalDate.of(2024, 5, 22));
        booking.setCheckOutDate(LocalDate.of(2024, 5, 23)); // 1 dag, inkluderar inte söndag-måndag


        bookingRepo.save(booking);

        double discount = discountService.getDiscount(testEmail, testBookingId);
        assertEquals(0.98, discount);
    }

    @Test
    public void testGetDiscount_NoRecentBookings_LongDuration_NoSundayToMonday() {
        testEmail = "customer@exampleDisc57.com";
        LocalDate checkInDate = LocalDate.of(2024, 5, 22);
        LocalDate checkOutDate = LocalDate.of(2024, 5, 24);

        Customer customer = new Customer( "Kalle", testEmail);
        Room room = new Room(1, false, 0, 100);
        customerRepo.save(customer);
        roomRepo.save(room);
        Booking booking = new Booking(checkInDate, checkOutDate, room, customer);
        bookingRepo.save(booking);

        double discount = discountService.getDiscount(testEmail, booking.getId());
        assertEquals(0.995, discount);
    }

    @Test
    public void testGetDiscount_NoRecentBookings_ShortDuration_SundayToMonday() {
        testEmail = "customer@example50.com";
        LocalDate checkInDate = LocalDate.of(2024, 5, 26);
        LocalDate checkOutDate = LocalDate.of(2024, 5, 27);

        Customer customer = new Customer(1L, "Kalle", testEmail);
        Room room = new Room(1, false, 0, 100);
        customerRepo.save(customer);
        roomRepo.save(room);
        Booking booking = new Booking(checkInDate, checkOutDate, room, customer);
        bookingRepo.save(booking);

        double discount = discountService.getDiscount(testEmail, booking.getId());
        assertEquals(0.98, discount);
    }

    @Test
    public void testGetDiscount_AllConditionsMet() {
        // Skapa 10 bokningar under senaste året
        long customerId = 1;
        for (int i = 0; i < 10; i++) {
            testEmail = "customer@example.com20";
            LocalDate checkInDate = LocalDate.now().minusMonths(2 + i);
            LocalDate checkOutDate = LocalDate.now().minusMonths(2 + i).plusDays(2);

            Customer customer = new Customer(customerId, "Kalle", testEmail);
            Room room = new Room(1, false, 0, 100);
            customerRepo.save(customer);
            roomRepo.save(room);
            Booking booking = new Booking(checkInDate, checkOutDate, room, customer);
            bookingRepo.save(booking);
        }

        Booking booking = bookingRepo.findById(testBookingId).get();
        booking.setCheckInDate(LocalDate.of(2024, 5, 24));
        booking.setCheckOutDate(LocalDate.of(2024, 5, 27)); // 3 dagar, inkluderar söndag-måndag
        bookingRepo.save(booking);

        double discount = discountService.getDiscount(testEmail, booking.getId());
        assertEquals(0.955, discount);
    }
}