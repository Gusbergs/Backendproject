package com.example.backendproject.service;

import Models.Bokningar;
import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.dto.RoomDtoMini;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RoomServiceTest {

    @MockBean
    RoomRepo roomRepo;

    @Mock
    CustomerRepo customerRepo;

    @Mock
    BookingRepo bookingRepo;

    @InjectMocks
    BookingService bookingService = new BookingService(roomRepo, customerRepo);
    @InjectMocks
    RoomService roomService = new RoomService(roomRepo, bookingService);

    long roomId = 1L;
    int roomNum = 4;
    boolean isDoubleRoom = false;
    int extrabed = 0;
    String customerName = "Krits";
    String eMail = "Krits@123";

    LocalDate start = LocalDate.of(2022, 12, 3);
    LocalDate stop = LocalDate.of(2022, 12, 24);






    /**/


    @BeforeEach
    public void init() {
        Room newRoom = new Room(roomNum, isDoubleRoom, extrabed);
        Customer newCustomer = new Customer(customerName, eMail);
        Booking newBooking = new Booking(start, stop, newRoom, newCustomer);
        RoomDtoMini roomDto = new RoomDtoMini(roomId, roomNum, isDoubleRoom, extrabed);

        RoomDtoDetailed roomDtoDetailed = RoomDtoDetailed.builder()
                .id(roomId)
                .roomNumber(roomNum)
                .doubleRoom(newRoom.isDoubleRoom())
                .extraBed(extrabed)
                .bookingDtoDetailedList(Arrays.asList(bookingService.bookingDtoDetailed(newBooking)))
                .build();

        when(roomRepo.findById(newRoom.getId())).thenReturn(Optional.of(newRoom));
        when(roomRepo.findAll()).thenReturn(Arrays.asList(newRoom));
    }

    /*@Test
    void roomDtoDetailed() {
        //RoomDtoDetailed actualRoomDto = roomService.roomDtoDetailed(newRoom);

    }

    @Test
    void roomDtoMini() {
    }

    @Test
    void getAllRoomsDetailed() {
        RoomService service2 = new RoomService(roomRepo, bookingService);

        List<RoomDtoDetailed> roomDtoList = service2.getAllRoomsDetailed();
        assertTrue(roomDtoList.size() == 0);
    }

    @Test
    void getAllRoomsMini() {
    }

    @Test
    void existsById() {
    }*/
}