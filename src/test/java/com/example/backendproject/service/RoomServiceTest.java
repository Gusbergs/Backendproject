package com.example.backendproject.service;

import com.example.backendproject.controller.BookingController;
import com.example.backendproject.dto.*;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class RoomServiceTest {

    @Mock
    RoomRepo roomRepo;
    @Mock
    BookingRepo bookingRepo;

    @Mock
    CustomerRepo customerRepo;

    @Mock
    QueueRepository queueRepo;

    @Autowired
    MockMvc mvc;





    @Autowired
    @InjectMocks
    BookingService bookingService;

    @Autowired
    @InjectMocks
    RoomService roomService;

    long id1 = 1L;
    int rn1 = 4;
    boolean dr1 = false;
    int eb1 = 0;
    long id2 = 2L;
    int rn2 = 2;
    boolean dr2 = true;
    int eb2 = 2;

    long cid1 = 1L;
    String cName = "Krits";
    String cMail = "Krits@123";

    long bid1 = 1L;
    LocalDate startDate = LocalDate.of(2024, 5, 1);
    LocalDate stopDate = LocalDate.of(2024, 5, 3);




    /* new Booking(bid1,startDate, stopDate, newRoom, newCustomer);

    CustomerDtoMini customerDtoMini = new CustomerDtoMini(cid1, cName, cMail);
    RoomDtoMini roomDtoMini = new RoomDtoMini(id1, rn1, dr1, eb1);
    BookingDtoDetailed bookingDto = BookingDtoDetailed.builder()
            .id(bid1)
            .checkInDate(startDate)
            .checkOutDate(stopDate)
            .roomDtoMini(roomDtoMini)
            .customerDtoMini(customerDtoMini)
            .build();
    RoomDtoDetailed roomDto = RoomDtoDetailed.builder()
            .id(id1)
            .roomNumber(rn1)
            .doubleRoom(dr1)
            .extraBed(eb1)
            .bookingDtoDetailedList(Arrays.asList(bookingDto))
            .build();


    */


    @BeforeEach
    public void init() {
        bookingService = new BookingService(roomRepo, customerRepo);
        roomService = new RoomService(roomRepo, bookingService, queueRepo);
       // List<Booking> bookingsList = Arrays.asList(newBooking);
        Room newRoom = new Room(id1, rn1, dr1, eb1);
        Room newRoom2 = new Room(id2, rn2, dr2, eb2);
        when(roomRepo.findById(1L)).thenReturn(Optional.of(newRoom));
        when(roomRepo.findById(2L)).thenReturn(Optional.of(newRoom2));
        //when(bookingService.bookingDtoDetailed(newBooking)).thenReturn(bookingDto);
    }

    /*@Test
    public void roomToRoomDtoDetail() {

        RoomDtoDetailed roomDtoActual = roomService.roomDtoDetailed(newRoom);

        assertNotNull(roomDtoActual);

        assertEquals(roomDtoActual.getId(), roomDto.getId());
        assertEquals(roomDtoActual.getRoomNumber(), roomDto.getRoomNumber());
        assertEquals(roomDtoActual.isDoubleRoom(), roomDto.isDoubleRoom());
        assertEquals(roomDtoActual.getExtraBed(), roomDto.getExtraBed());


        assertEquals(roomDtoActual.getBookingDtoDetailedList().get(0).getId(), roomDto.getBookingDtoDetailedList().get(0).getId());
        assertEquals(roomDtoActual.getBookingDtoDetailedList().get(0).getCheckInDate(), roomDto.getBookingDtoDetailedList().get(0).getCheckInDate());
        assertEquals(roomDtoActual.getBookingDtoDetailedList().get(0).getCheckOutDate(), roomDto.getBookingDtoDetailedList().get(0).getCheckOutDate());
    }*/


    @Test
    void existsById() {
        //System.out.println(roomRepo.findById(1L));
        //System.out.println(roomRepo.findById(3L).isPresent());
        //System.out.println("RoomServiceExist: "+roomService.existsById(3L));

        assertThat(roomService.existsById(1L)).isTrue();
        assertThat(roomService.existsById(3L)).isFalse();
    }
}