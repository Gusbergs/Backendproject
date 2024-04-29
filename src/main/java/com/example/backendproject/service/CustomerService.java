package com.example.backendproject.service;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {


    final private CustomerRepo customerRepo;

    public CustomerDtoDetailed customerDtoDetailed(Customer customer) {
        List<BookingDtoMini> bookingDtos = customer.getBookings().stream()
                .map(booking -> new BookingDtoMini(booking.getId(), booking.getCheckInDate(), booking.getCheckOutDate()))
                .collect(Collectors.toList());

        return CustomerDtoDetailed.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .bookingDtoMini(bookingDtos)
                .build();
    }

    public CustomerDtoMini customerDtoMini(Customer customer) {
        return CustomerDtoMini.builder().id(customer.getId()).name(customer.getName()).email(customer.getEmail()).build();
    }


    public List<CustomerDtoDetailed> getAllCustomersDetailed(){
        return customerRepo.findAll().stream().map(customer -> customerDtoDetailed(customer)).toList();
    }

    public List<CustomerDtoMini> getAllCustomersMini(){
        return customerRepo.findAll().stream().map(customer -> customerDtoMini(customer)).toList();
    }

    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }


    public boolean checkIfCustomerExist(String email){
        return customerRepo.findByEmail(email).isPresent();
    }

}
