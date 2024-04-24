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
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private CustomerDtoMini customerDtoMini;
    private RoomDtoMini roomDtoMini;

}
