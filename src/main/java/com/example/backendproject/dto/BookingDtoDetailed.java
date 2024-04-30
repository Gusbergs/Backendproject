package com.example.backendproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDtoDetailed {
    long id;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    CustomerDtoMini customerDtoMini;
    RoomDtoMini roomDtoMini;
}
