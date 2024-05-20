package com.example.backendproject.service;

import com.example.backendproject.models.Booking;
import com.example.backendproject.repo.BookingRepo;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        List<Booking> bookings = bookingRepo.findRecentBookingsByCustomerEmail(email, oneYearAgo);
        System.out.println(bookings.size());
        return bookings.size() >= 10; // Returnerar true om antalet bokningar är 10 eller fler
    }

    public boolean isDurationAtLeastTwoDays(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepo.findById(bookingId);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            LocalDate checkInDate = booking.getCheckInDate();
            LocalDate checkOutDate = booking.getCheckOutDate();

            long daysBetween = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            return daysBetween >= 2;
        }
        return false;
    }

    public boolean includesSundayToMonday(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepo.findById(bookingId);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            LocalDate startDate = booking.getCheckInDate();
            LocalDate endDate = booking.getCheckOutDate();

            LocalDate date = startDate;
            while (date.isBefore(endDate)) {  // Kolla varje dag tills dagen innan utcheckning
                if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    if (date.plusDays(1).getDayOfWeek() == DayOfWeek.MONDAY) {
                        return true;  // Returnerar true om en söndag följs av en måndag
                    }
                }
                date = date.plusDays(1);
            }
        }
        System.out.println("Bokningen är inte över natten söndag-måndag");
        return false;
    }

    public double getDiscount(String email, Long bookingId) {

        double discount = 1;

        if (getRecentBookingsByCustomerEmail(email)) {
            discount -= 0.02;
            System.out.println("Kunden är storkund");
        }
        if (isDurationAtLeastTwoDays(bookingId)) {
            discount -= 0.005;
            System.out.println("Bokningen är minst två nätter");
        }
        if (includesSundayToMonday(bookingId)){
            discount -= 0.02;
            System.out.println("Bokningen går från söndag till måndag");
        }
        return discount;
    }


}
