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
    public void showAllBookingDtoDetailed() {
        for (RoomDtoDetailed getRoom : roomService.getAllRoomsDetailed()) {
            System.out.println(getRoom.getId() + " " + getRoom.getRoomNumber() + " " + getRoom.getExtraBed());
        }
    }
}