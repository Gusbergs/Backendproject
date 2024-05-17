package com.example.backendproject.models;

import lombok.Builder;

import java.time.LocalDateTime;



public class EventViewModel {
    private Long id;
    private String roomNo;
    private LocalDateTime timeStamp;
    private String type;
    private String cleaningByUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCleaningByUser() {
        return cleaningByUser;
    }

    public void setCleaningByUser(String cleaningByUser) {
        this.cleaningByUser = cleaningByUser;
    }
}
