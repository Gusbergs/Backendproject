package com.example.backendproject.service;


import com.example.backendproject.repo.BookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private BookingRepo bookingRepo;
}
