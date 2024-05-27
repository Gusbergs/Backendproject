package com.example.backendproject.models.QueueModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class RoomCleaningFinished extends QueueModel {
    @JsonProperty("CleaningByUser")
    private String cleaningByUser;

    public void setCleaningByUser(String user) {
        this.cleaningByUser = user;
    }

    public String getCleaningByUser() {
        return cleaningByUser;
    }
}
