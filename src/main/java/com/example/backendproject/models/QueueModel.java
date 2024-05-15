package com.example.backendproject.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
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
    private String type;

    @JsonProperty("RoomNo")
    private String RoomNo;

    @JsonProperty("TimeStamp")
    private LocalDateTime TimeStamp;

    @JsonProperty("CleaningByUser")
    private String CleaningByUser;

}

@Data
@NoArgsConstructor
@Entity
class RoomCleaningFinished extends QueueModel {
    private String CleaningByUser;
}

@Data
@NoArgsConstructor
@Entity
class RoomClosed extends QueueModel {}

@Data
@NoArgsConstructor
@Entity
class RoomOpened extends QueueModel {}

@Data
@NoArgsConstructor
@Entity
class RoomCleaningStarted extends QueueModel {
    private String CleaningByUser;
}
