package com.example.backendproject.service;


import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.dto.RoomDtoMini;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    final private RoomRepo roomRepo;

   public RoomDtoDetailed roomDtoDetailed(Room room) {
       List<BookingDtoMini> bookingDtos = room.getBookings().stream()
               .map(booking -> new BookingDtoMini(booking.getId(), booking.getCheckInDate(), booking.getCheckOutDate()))
               .collect(Collectors.toList());

       return RoomDtoDetailed.builder()
               .id(room.getId())
               .doubleRoom(room.isDoubleRoom())
               .extraBed(room.getExtraBed())
               .build();
   }

   public RoomDtoMini roomDtoMini(Room room) {
       return RoomDtoMini.builder().id(room.getId()).roomNumber(room.getRoomNumber()).doubleRoom(room.isDoubleRoom()).extraBed(room.getExtraBed()).build();
   }
public List<RoomDtoDetailed> getAllRoomsDetailed(){
       return roomRepo.findAll().stream().map(room -> roomDtoDetailed(room)).toList();
}

    public List<RoomDtoMini> getAllRoomsMini(){
        return roomRepo.findAll().stream().map(room -> roomDtoMini(room)).toList();
    }

    public boolean existsById(Long roomId) {
        return roomRepo.existsById(roomId);
    }

}
