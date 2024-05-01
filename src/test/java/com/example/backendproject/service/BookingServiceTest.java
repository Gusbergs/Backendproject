package com.example.backendproject.service;
import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate; import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class BookingServiceTest {
    @Mock
    BookingRepo bookingRepo;
    @Mock
    CustomerRepo customerRepo;
    @Mock
    RoomRepo roomRepo;

    @InjectMocks BookingService bookingService = new BookingService(roomRepo, customerRepo);

    LocalDate start = LocalDate.of(2022, 12, 3);
    LocalDate stop = LocalDate.of(2022, 12, 24);

    int roomNum = 4;
    boolean isDoubleRoom = false;
    int extrabed = 0;
    String customerName = "Krits";
    String eMail = "Krits@123";

    Room newRoom = new Room(roomNum, isDoubleRoom, extrabed);
    Customer newCustomer = new Customer(customerName, eMail);

    Booking booking = new Booking(start, stop, newRoom, newCustomer);


    @Test
    void bookingTest() {
        when(bookingRepo.save(booking)).thenReturn(booking);
        when(customerRepo.save(newCustomer)).thenReturn(newCustomer);
        BookingService service2 = new BookingService(roomRepo, customerRepo);
        List<BookingDtoDetailed> bookedList = service2.getAllBookingsDetailed();

        assertTrue(bookedList.size() == 1);
    }

}