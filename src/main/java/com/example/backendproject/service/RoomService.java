package com.example.backendproject.service;


import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    final private RoomRepo repo;

   /* public RoomDtoDetailed roomDtoDetailed(Room room) {
        return RoomDtoDetailed.builder().id(room.getId()).roomNumber(room.getRoomNumber()).doubleRoom(room.getDoubleroom)
    }

    */

}
