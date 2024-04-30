package com.example.backendproject.repo;

import com.example.backendproject.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND NOT (b.checkOutDate <= :start OR b.checkInDate >= :end)")
    List<Booking> findBookingsByDateRangeAndRoom(@Param("roomId") Long roomId, @Param("start") LocalDate start, @Param("end") LocalDate end);

}
