package com.example.backendproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDtoDetailed {
    long id;
    int roomNumber;
    boolean doubleRoom;
    int extraBed;

    //BookingDtoMini bookingDtoMini;

    private List<BookingDtoDetailed> bookingDtoDetailedList;




}
