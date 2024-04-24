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
public class BookingDtoMini {
    long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
