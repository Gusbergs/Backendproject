package com.example.backendproject.repo;

import com.example.backendproject.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerId(Long customerId);
    Optional<Booking> findById(Long bookingId);

    @Query("SELECT b FROM Booking b WHERE b.customer.email = :customerEmail AND b.checkInDate > :oneYearAgo")
    List<Booking> findRecentBookingsByCustomerEmail(@Param("customerEmail") String customerEmail, @Param("oneYearAgo") LocalDate oneYearAgo);

}
