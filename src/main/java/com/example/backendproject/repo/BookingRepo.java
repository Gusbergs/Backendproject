package com.example.backendproject.repo;

import com.example.backendproject.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerId(Long customerId);
    Optional<Booking> findById(Long bookingId);

    // JPQL Query för att hämta bokningar baserat på kundens e-post och filtrera de som är äldre än 1 år
    @Query("SELECT b FROM Booking b WHERE b.customer.email = :customerEmail AND b.checkInDate > :oneYearAgo")
    List<Booking> findRecentBookingsByCustomerEmail(@Param("customerEmail") String customerEmail, @Param("oneYearAgo") Date oneYearAgo);

    @Query(value = "SELECT CASE WHEN DATEDIFF(b.check_out_date, b.check_in_date) >= 2 THEN true ELSE false END FROM Booking b WHERE b.id = :bookingId", nativeQuery = true)
    boolean checkBookingDurationById(@Param("bookingId") Long bookingId);
}
