package com.example.backendproject.service;


import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    final private RoomRepo roomRepo;


    @Autowired
    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public List<Room> getAllRooms() {

        return roomRepo.findAll();
    }


}
