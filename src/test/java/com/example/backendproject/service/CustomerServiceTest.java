package com.example.backendproject.service;


import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {
    @Autowired
    CustomerServiceTest(CustomerService customerService,RoomRepo roomRepo, BookingRepo bookingRepo){
        this.customerService = customerService;
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

    CustomerService customerService;
    RoomRepo roomRepo;
    BookingRepo bookingRepo;


    @Test
    public void tryingToSaveCustomer(){
        Customer customer = new Customer("Bob","bobs@email.se");

        customerService.saveCustomer(customer);

        assertTrue(customerService.checkIfCustomerExist("bobs@email.se"));
    }

    @Test
    void checkIfCustomerExist() {
        Customer customer = new Customer("Bob","charlie@gmail.com");

        customerService.saveCustomer(customer);
        Boolean result = customerService.checkIfCustomerExist("charlie@gmail.com");

        assertThat(result).isTrue();
    }
    @Test
    void checkIfCustomerDoesNotExist() {
        String email ="DoesNotExist@gmail.com";

        Boolean result = customerService.checkIfCustomerExist(email);

        assertThat(result).isFalse();
    }

    @Test
    @Transactional
    void tryingToDeleteCustomer() {

        Customer customer = new Customer(5L, "Test", "test@example.com");
        customerService.saveCustomer(customer);

        //verifying
        Boolean result2 = customerService.checkIfCustomerExist("test@example.com");
        System.out.println("result2: "+result2);
        assertThat(result2).isTrue();

        //Deleting
        customerService.deleteCustomerByEmail("test@example.com");

        //verifying
        Boolean result3 = customerService.checkIfCustomerExist("test@example.com");
        assertThat(result3).isFalse();

    }

    @Test
    @Transactional
    void checkWhenCustomerHasABooking(){

        //book a room
        //check if it works
        LocalDate i1 = LocalDate.of(1999, 12, 11);
        LocalDate i2 = LocalDate.of(2021, 1, 11);

        Customer customer = new Customer("Viktor", "viktor@mail.com");
        customerService.saveCustomer(customer);

        Room r1 = new Room(111, false, 0);
        roomRepo.save(r1);

        Booking booking1 = new Booking(i1, i2, r1, customer);
        bookingRepo.save(booking1);

        var result = customerService.customerHasABooking(customer.getId());

        assertThat(result).isTrue();


    }
    @Test
    @Transactional
    void checkWhenCustomerDoesNotHaveABooking(){

        //book a room
        //check if it works
        LocalDate i1 = LocalDate.of(1999, 12, 11);
        LocalDate i2 = LocalDate.of(2021, 1, 11);

        Customer customer = new Customer("Viktor", "viktor@mail.com");
        Customer customer2 = new Customer("Mario", "Mario@mail.se");
        customerService.saveCustomer(customer);
        customerService.saveCustomer(customer2);

        Room r1 = new Room(111, false, 0);
        roomRepo.save(r1);

        Booking booking1 = new Booking(i1, i2, r1, customer);
        bookingRepo.save(booking1);

        var result = customerService.customerHasABooking(customer2.getId());

        assertThat(result).isFalse();


    }

}