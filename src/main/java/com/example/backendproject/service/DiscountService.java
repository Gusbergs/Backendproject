package com.example.backendproject.service;

import com.example.backendproject.models.Booking;
import com.example.backendproject.repo.BookingRepo;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {

    @Autowired
    private BookingRepo bookingRepo;

    public List<Booking> getRecentBookingsByCustomerEmail(String email) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1); // Subtraherar ett år från dagens datum
        Date oneYearAgo = cal.getTime();

        return bookingRepo.findRecentBookingsByCustomerEmail(email, oneYearAgo);
    }

    public boolean isDurationAtLeastTwoDays(Long bookingId) {
        return bookingRepo.checkBookingDurationById(bookingId);
    }




}
