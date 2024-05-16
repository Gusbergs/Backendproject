package com.example.backendproject.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RoomCleaningFinished.class, name = "RoomCleaningFinished"),
        @JsonSubTypes.Type(value = RoomClosed.class, name = "RoomClosed"),
        @JsonSubTypes.Type(value = RoomOpened.class, name = "RoomOpened"),
        @JsonSubTypes.Type(value = RoomCleaningStarted.class, name = "RoomCleaningStarted")
})
public abstract class QueueModel {
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("RoomNo")
    private String roomNo;

    @JsonProperty("TimeStamp")
    private LocalDateTime timeStamp;


}

@Entity
@Data
class RoomCleaningFinished extends QueueModel {
    @JsonProperty("CleaningByUser")
    private String cleaningByUser;
}

@Entity
@Data
class RoomClosed extends QueueModel {}

@Entity
@Data
class RoomOpened extends QueueModel {}

@Entity
@Data
class RoomCleaningStarted extends QueueModel {
    @JsonProperty("CleaningByUser")
    private String cleaningByUser;
}
