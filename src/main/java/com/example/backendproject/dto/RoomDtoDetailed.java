package com.example.backendproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDtoDetailed {
    long id;
    int roomNumber;
    boolean doubleRoom;
    int extraBed;

    BookingDtoMini bookingDtoMini;



}
