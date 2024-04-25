package com.example.backendproject.service;


import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.models.Customer;
import com.example.backendproject.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    private final BookingService bookingService;

    CustomerService(CustomerRepo customerRepo, BookingService bookingService) {
        this.customerRepo = customerRepo;
        this.bookingService = bookingService;
    }

    public List<CustomerDtoDetailed> getAllListDtoDetailed() {
        return customerRepo
                .findAll()
                .stream()
                .map(customer -> CustomerToCustomerDetailedDto(customer))
                .toList();
    }

    public CustomerDtoDetailed CustomerToCustomerDetailedDto(Customer customer) {
        return CustomerDtoDetailed.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .bookingDtoList(customer
                        .getBookingList()
                        .stream()
                        .map(booking -> bookingService.bookingDtoDetailed(booking))
                        .toList())
                .build();
    }



}
