package com.example.backendproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {

    private long id;
    private int roomNumber;
    private boolean doubleRoom;
    private int extraBed;

}
