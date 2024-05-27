package com.example.backendproject.models.QueueModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class RoomCleaningStarted extends QueueModel {
    @JsonProperty("CleaningByUser")
    private String cleaningByUser;

    public String getCleaningByUser() {
        return cleaningByUser;
    }

    public void setCleaningByUser(String cleaningByUser) {
        this.cleaningByUser = cleaningByUser;
    }
}
