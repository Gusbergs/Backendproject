package com.example.backendproject.repo;

import com.example.backendproject.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
