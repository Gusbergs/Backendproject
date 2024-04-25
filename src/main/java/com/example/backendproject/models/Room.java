package com.example.backendproject.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {



    @Id
    @GeneratedValue
    long id;

    int roomNumber;

    boolean doubleRoom;

    int extraBed;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookingList;


    public Room(int roomNumber, boolean doubleRoom, int extraBed){
        this.roomNumber = roomNumber;
        this.doubleRoom = doubleRoom;
        this.extraBed = extraBed;
    }


}
