package com.example.backendproject.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    long id;

    int roomNumber;

    boolean doubleRoom;

    int extraBed;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public Room(int roomNumber, boolean doubleRoom, int extraBed){
        this.roomNumber = roomNumber;
        this.doubleRoom = doubleRoom;
        this.extraBed = extraBed;
    }


}
