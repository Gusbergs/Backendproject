package com.example.backendproject.controller;

import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.RoomRepo;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BookingControllerTest {

    @Autowired
    BookingController bookingController;
    @Autowired
    BookingService bookingService;

    @Autowired
    RoomService roomService;

    @Autowired
    RoomRepo roomRepo;



    @Test
    void findCrossedTime() {
        LocalDate startDate = LocalDate.of(2020, 10, 1);
        LocalDate stopDate = LocalDate.of(2020, 10, 5);

        LocalDate startDate2 = LocalDate.of(2022, 1, 2);
        LocalDate stopDate2 = LocalDate.of(2023, 3, 21);

        LocalDate startDate3 = LocalDate.of(2022, 12, 2);
        LocalDate stopDate3 = LocalDate.of(2023, 2, 16);

        RoomDtoDetailed roomDtoDetailed = null;

        boolean isTheRoomExist = false;
        for (RoomDtoDetailed getRoom : roomService.getAllRoomsDetailed()) {
            if (getRoom.getId() == 154L) {
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

    @Test
    public void showAllBookingDtoDetailed() {
        for (RoomDtoDetailed getRoom : roomService.getAllRoomsDetailed()) {
            System.out.println(getRoom.getId() + " " + getRoom.getRoomNumber() + " " + getRoom.getExtraBed());
        }
    }
}