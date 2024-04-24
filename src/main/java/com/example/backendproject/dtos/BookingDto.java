package com.example.backendproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {
    private long id;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private RoomDto roomDto;
    private CustomerDto customerDto;
}
