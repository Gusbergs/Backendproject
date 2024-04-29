package com.example.backendproject.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {


    @Id
    @GeneratedValue
    long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;






    @ManyToOne
    @JoinColumn
    private Room room;


    @ManyToOne
    @JoinColumn
    private Customer customer;


    public Booking (LocalDate checkInDate, LocalDate checkOutDate, Room room, Customer customer){
        this.checkInDate= checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.customer = customer;

    }


}
