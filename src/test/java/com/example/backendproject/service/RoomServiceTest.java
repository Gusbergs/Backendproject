package com.example.backendproject.service;

import com.example.backendproject.controller.BookingController;
import com.example.backendproject.dto.*;
import com.example.backendproject.models.*;
import com.example.backendproject.models.QueueModels.*;
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
import java.time.LocalDateTime;
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

    Room newRoom = new Room(id1, rn1, dr1, eb1);
    Room newRoom2 = new Room(id2, rn2, dr2, eb2);

    List<Room> roomLists = List.of(newRoom, newRoom2);



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


        when(roomRepo.findById(1L)).thenReturn(Optional.of(newRoom));
        when(roomRepo.findById(2L)).thenReturn(Optional.of(newRoom2));
        when(roomRepo.saveAll(anyList())).thenReturn(roomLists);
        when(roomRepo.findAll()).thenReturn(roomLists);
        //when(bookingService.bookingDtoDetailed(newBooking)).thenReturn(bookingDto);
    }

    @Test
    void existsById() {
        //System.out.println(roomRepo.findById(1L));
        //System.out.println(roomRepo.findById(3L).isPresent());
        //System.out.println("RoomServiceExist: "+roomService.existsById(3L));

        assertThat(roomService.existsById(1L)).isTrue();
        assertThat(roomService.existsById(3L)).isFalse();
    }

    @Test
    void roomDtoDetailedTest() {
        RoomDtoDetailed newRoomDtoDt = roomService.roomDtoDetailed(newRoom);

        System.out.println("RoomNumTest: "+newRoomDtoDt.getRoomNumber());

        assertEquals(4, newRoomDtoDt.getRoomNumber());
        assertFalse(newRoomDtoDt.isDoubleRoom());
        assertEquals(0, newRoomDtoDt.getExtraBed());
    }

    @Test
    void roomDtoMiniTest() {
        Room newRoom3 = new Room(5, false, 0, 100);
        RoomDtoMini newRoomDtoMini = roomService.roomDtoMini(newRoom3);

        assertEquals(5, newRoomDtoMini.getRoomNumber());
        assertFalse(newRoomDtoMini.isDoubleRoom());
        assertEquals(100, newRoomDtoMini.getPrice());
    }

    @Test
    void getAllRoomsDetailedTest() {
        List<RoomDtoDetailed> rooms = roomService.getAllRoomsDetailed();
        System.out.println(rooms.get(0));

        assertEquals(4, rooms.get(0).getRoomNumber());
        assertEquals(2, rooms.get(1).getRoomNumber());
        assertFalse(rooms.get(0).isDoubleRoom());
        assertTrue(rooms.get(1).isDoubleRoom());
        assertEquals(0, rooms.get(0).getExtraBed());
        assertEquals(2, rooms.get(1).getExtraBed());
    }

    @Test
    void getAllRoomsMiniTest() {
        List<RoomDtoMini> roomMinis = roomService.getAllRoomsMini();

        assertEquals(4, roomMinis.get(0).getRoomNumber());
        assertEquals(2, roomMinis.get(1).getRoomNumber());
        assertFalse(roomMinis.get(0).isDoubleRoom());
        assertTrue(roomMinis.get(1).isDoubleRoom());
        assertEquals(0, roomMinis.get(0).getExtraBed());
        assertEquals(2, roomMinis.get(1).getExtraBed());
    }

    @Test
    void roomDtoToRoomTest() {
        RoomDtoDetailed newRoomDtoDt = roomService.roomDtoDetailed(newRoom);
        Room roomDtoToRoom = roomService.RoomDtoToRoom(newRoomDtoDt);

        assertEquals(4, roomDtoToRoom.getRoomNumber());
    }

    @Test
    void getRoomByIdTest() {
        assertThat(roomService.getRoomById(1L)).isPresent();
        assertThat(roomService.getRoomById(2L)).isPresent();
        assertThat(roomService.getRoomById(3L)).isNotPresent();
    }

    @Test
    void getEventsByRoomNoTest() {
        QueueModel queue1 = new RoomOpened();
        queue1.setId(1L);
        queue1.setRoomNo("4");
        queue1.setTimeStamp(LocalDateTime.of(2024, 5, 28, 6, 13, 4));

        RoomCleaningStarted queue2 = new RoomCleaningStarted();
        queue2.setId(2L);
        queue2.setRoomNo("4");
        queue2.setTimeStamp(LocalDateTime.of(2024, 5, 28, 6, 13, 5));
        queue2.setCleaningByUser("Roy Mustang");

        RoomCleaningFinished queue3 = new RoomCleaningFinished();
        queue2.setId(2L);
        queue2.setRoomNo("4");
        queue2.setTimeStamp(LocalDateTime.of(2024, 5, 28, 6, 13, 15));
        queue2.setCleaningByUser("Roy Mustang");

        QueueModel queue4 = new RoomClosed();
        queue4.setId(1L);
        queue4.setRoomNo("4");
        queue4.setTimeStamp(LocalDateTime.of(2024, 5, 28, 6, 13, 16));

        List<QueueModel> queueModelList = Arrays.asList(queue1, queue2, queue3, queue4);
        when(queueRepo.findByRoomNo("4")).thenReturn(queueModelList);

        // Act
        List<QueueModel> result = roomService.getEventsByRoomNo("4");

        // Assert
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("4", result.get(0).getRoomNo());
        assertTrue(result.get(0) instanceof RoomOpened);
        assertTrue(result.get(1) instanceof RoomCleaningStarted);
        assertTrue(result.get(2) instanceof RoomCleaningFinished);
        assertTrue(result.get(3) instanceof RoomClosed);

        verify(queueRepo, times(1)).findByRoomNo("4");

    }
}