package com.example.backendproject.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Booking {


    @Id
    @GeneratedValue
    long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    public void setCheckOutDate(LocalDate checkOutDate) {
        if (checkOutDate.isBefore(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
        this.checkOutDate = checkOutDate;
    }




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
