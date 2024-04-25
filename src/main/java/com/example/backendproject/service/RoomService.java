package com.example.backendproject.service;


import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepo repo;

    private final BookingService bookingService;

    public RoomService(RoomRepo repo, BookingService bookingService) {
        this.repo = repo;
        this.bookingService = bookingService;
    }

    public List<RoomDtoDetailed> getAllRoomList() {
        return repo.findAll().stream().map(room -> RoomToRoomDetailedDto(room)).toList();
    }


    public RoomDtoDetailed RoomToRoomDetailedDto(Room room) {
        return RoomDtoDetailed.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .doubleRoom(room.isDoubleRoom())
                .bookingDtoDetailedList(room
                        .getBookingList()
                        .stream()
                        .map(booking -> bookingService.bookingDtoDetailed(booking))
                        .toList())
                .build();
    }

}
