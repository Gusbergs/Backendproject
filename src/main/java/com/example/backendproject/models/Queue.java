package com.example.backendproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Queue {


    @Id
    @GeneratedValue
    public long id;

    public String type;

    public LocalDateTime TimeStamp;

    public int RoomNo;

    public String CleaningByUser;

    public Queue(String type, LocalDateTime TimeStamp, int RoomNo) {
        this.type = type;
        this.TimeStamp = TimeStamp;
        this.RoomNo = RoomNo;
    }

    public void setCleaningByUser(String cleaningByUser) {
        this.CleaningByUser = cleaningByUser;
    }
}
