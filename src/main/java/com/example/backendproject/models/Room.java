package com.example.backendproject.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    public Room(int roomNumber, boolean doubleRoom, int extraBed){
        this.roomNumber = roomNumber;
        this.doubleRoom = doubleRoom;
        this.extraBed = extraBed;
    }


}
