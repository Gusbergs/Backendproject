package com.example.backendproject.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue
    long id;

    int roomNumber;

    boolean doubleRoom;

    int extraBed;

    double price;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public Room(int roomNumber, boolean doubleRoom, int extraBed, double price){
        this.roomNumber = roomNumber;
        this.doubleRoom = doubleRoom;
        this.extraBed = extraBed;
        this.price = price;
    }
   public Room(Long id,int roomNumber, boolean doubleRoom, int extraBed){
        this.id = id;
        this.roomNumber = roomNumber;
        this.doubleRoom = doubleRoom;
        this.extraBed = extraBed;
    }




}
