package com.example.backendproject.service;


import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.models.Customer;
import com.example.backendproject.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;

    private final BookingService bookingService;

    public List<CustomerDtoDetailed> getAllListDtoDetailed() {
        return customerRepo
                .findAll()
                .stream()
                .map(customer -> CustomerToCustomerDetailedDto(customer))
                .toList();
    }

    /*public CustomerDtoMini CustomerToCustomerDtoMini(Customer customer) {

    }*/

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
