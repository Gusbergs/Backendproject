package com.example.backendproject.service;

import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BookingServiceTest {

    @MockBean
   private BookingRepo bookingRepo;
    @MockBean
    private CustomerRepo customerRepo;
    @MockBean
    RoomRepo roomRepo;

    private Booking createMockBooking(Long id) {
        Customer customer = new Customer("Customer" + id, "customer" + id + "@example.com");
        Room room = new Room(100 + id.intValue(), id % 2 == 0, 1);
        return new Booking(id, LocalDate.now(), LocalDate.now().plusDays(1), room, customer);
    }



    @InjectMocks
    @Autowired
    BookingService bookingService = new BookingService(roomRepo, customerRepo);
    @InjectMocks
    RoomService roomService = new RoomService(roomRepo, bookingService);

        Customer customer = new Customer("John Doe", "john.doe@example.com");
        Room room = new Room(101, true, 1);

        // Skapa Booking-objekt
        Booking booking = new Booking(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 10), room, customer);

   /* @BeforeEach
    public void init(){

    long id = 1L;
    LocalDate checkIn = LocalDate.of(2022, 11, 1);
    LocalDate checkOut = LocalDate.of(2022, 11, 23);
    Room room = new Room(1, true, 1);
    Customer customer = new Customer("kalle ", "Hej@kalle.123");
    Booking booking = new Booking(checkIn, checkOut, room, customer);
    RoomDtoDetailed roomDtoDetailed = roomService.roomDtoDetailed(room);

    when(roomService.getAllRoomsDetailed()).thenReturn((List<RoomDtoDetailed>) roomDtoDetailed);



    }

    */


    @Test
    void bookingDtoDetailed() {

        BookingDtoDetailed result = bookingService.bookingDtoDetailed(booking);

        assertAll(
                () -> assertEquals(booking.getId(), result.getId(), "Booking ID should match"),
                () -> assertEquals(booking.getCheckInDate(), result.getCheckInDate(), "Check-in dates should match"),
                () -> assertEquals(booking.getCheckOutDate(), result.getCheckOutDate(), "Check-out dates should match"),
                () -> assertNotNull(result.getCustomerDtoMini(), "Customer DTO should not be null"),
                () -> assertEquals(customer.getId(), result.getCustomerDtoMini().getId(), "Customer IDs should match"),
                () -> assertEquals(customer.getName(), result.getCustomerDtoMini().getName(), "Customer names should match"),
                () -> assertEquals(customer.getEmail(), result.getCustomerDtoMini().getEmail(), "Customer emails should match"),
                () -> assertNotNull(result.getRoomDtoMini(), "Room DTO should not be null"),
                () -> assertEquals(room.getId(), result.getRoomDtoMini().getId(), "Room IDs should match"),
                () -> assertEquals(room.getRoomNumber(), result.getRoomDtoMini().getRoomNumber(), "Room numbers should match"),
                () -> assertEquals(room.isDoubleRoom(), result.getRoomDtoMini().isDoubleRoom(), "Room type should match"),
                () -> assertEquals(room.getExtraBed(), result.getRoomDtoMini().getExtraBed(), "Extra bed status should match")
        );
    }

    @Test
    void bookingtoDtoMini() {
        BookingDtoMini result = bookingService.bookingtoDtoMini(booking);
        assertAll(
                () -> assertEquals(booking.getId(), result.getId(), "Booking ID should match"),
                () -> assertEquals(booking.getCheckInDate(), result.getCheckInDate(), "Check-in dates should match"),
                () -> assertEquals(booking.getCheckOutDate(), result.getCheckOutDate(), "Check-out dates should match")
        );
    }

  /* @Test
    void findCrossedTime(){
        boolean isTheRoomExist = false;
        for (RoomDtoDetailed getRoom : roomService.getAllRoomsDetailed()) {
            if (getRoom.getId() == 1L) {
                roomDtoDetailed = getRoom;
                isTheRoomExist = true;
                break;
            }
        }
        if (isTheRoomExist) {
            System.out.println(roomDtoDetailed.getId() + " " + roomDtoDetailed.getRoomNumber());
        } else {
            System.out.println("Can't find the id '154'");
        }

        System.out.println(bookingService.findCrossedTime(startDate, stopDate, roomDtoDetailed));
        System.out.println(bookingService.findCrossedTime(startDate2, stopDate2, roomDtoDetailed));

        assertTrue(bookingService.findCrossedTime(startDate, stopDate, roomDtoDetailed));
        assertTrue(bookingService.findCrossedTime(startDate2, stopDate2, roomDtoDetailed));
        assertTrue(bookingService.findCrossedTime(startDate3, stopDate3, roomDtoDetailed));
    }

   */



    @Test
    void getBookingById2() {
    }

  /*  @Test
    void getAllBookingsDetailed() {
        List<Booking> mockBookings = Arrays.asList(
                createMockBooking(1L),
                createMockBooking(2L),
                createMockBooking(3L)
        );

        when(bookingRepo.findAll()).thenReturn(mockBookings);

        // Call the method under test
        List<BookingDtoDetailed> result = bookingService.getAllBookingsDetailed();

        // Assertions
        assertEquals(3, result.size(), "Should return a list of 3 detailed bookings");
        verify(mockBookings).findAll(); // Verify that findAll was called
        mockBookings.forEach(booking ->
                verify(bookingService).bookingDtoDetailed(booking) // Verify that bookingDtoDetailed was called for each booking
        );
    }

   */

    @Test
    void getAllBookingsMini() {
    }

    @Test
    void findAllBookings() {
    }

    @Test
    void deleteBookingById() {
    }

    @Test
    void findBookingById() {
    }

    @Test
    void updateBookingById() {
    }
}