package com.example.backendproject.service;

import com.example.backendproject.models.Booking;
import com.example.backendproject.repo.BookingRepo;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {

    @Autowired
    private BookingRepo bookingRepo;

    public boolean getRecentBookingsByCustomerEmail(String email) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1); // Subtraherar ett år från dagens datum
        Date oneYearAgo = cal.getTime();

        List<Booking> bookings = bookingRepo.findRecentBookingsByCustomerEmail(email, oneYearAgo);
        System.out.println(bookings.size());
        return bookings.size() >= 10; // Returnerar true om antalet bokningar är 10 eller fler
    }

    public boolean isDurationAtLeastTwoDays(Long bookingId) {
        return bookingRepo.checkBookingDurationById(bookingId);
    }

    public boolean isBookingFromSundayToMonday(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepo.findById(bookingId);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            DayOfWeek checkInDay = booking.getCheckInDate().getDayOfWeek();
            DayOfWeek checkOutDay = booking.getCheckOutDate().getDayOfWeek();

            return checkInDay == DayOfWeek.SUNDAY && checkOutDay == DayOfWeek.MONDAY;
        }
        return false;
    }


    public double getDiscount(String email, Long bookingId) {

        double discount = 1;

        if (getRecentBookingsByCustomerEmail(email) && isDurationAtLeastTwoDays(bookingId)) {
            discount =-0.025;
        }
        return discount;
    }


}
