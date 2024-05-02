package com.example.backendproject.service;

import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerServiceTest {

    @Autowired
    @InjectMocks
    CustomerService customerService;

    @Autowired
    MockMvc mvc;

    @MockBean
    private CustomerRepo mockRepo;


   @BeforeEach
   public void init(){
       Customer c1 = new Customer(1L,"Adam","adam123@gmail.se");
       Customer c2 = new Customer(2L,"Bob","bob@gmail.se");
       Customer c3 = new Customer(3L,"Charlie","charlie@gmail.com");

       when(mockRepo.findByEmail("adam123@gmail.se")).thenReturn(Optional.of(c1));
       when(mockRepo.findByEmail("bob@gmail.se")).thenReturn(Optional.of(c2));
       when(mockRepo.findByEmail("charlie@gmail.com")).thenReturn(Optional.of(c3));
   }


    @Test
    void checkIfCustomerExist() {
       String email = "charlie@gmail.com";

       Boolean result = customerService.checkIfCustomerExist(email);

       assertThat(result).isTrue();
    }
    @Test
    void checkIfCustomerDoesNotExist() {
        String email ="DoesNotExist@gmail.com";


        Boolean result = customerService.checkIfCustomerExist(email);

        assertThat(result).isFalse();
    }

    @Test
    void saveCustomer() {
        Customer customer = new Customer(4L, "Test User", "test@example.com");

        customerService.saveCustomer(customer);

        verify(mockRepo).save(customer);
    }


    @Test
    void deleteCustomer() {

        Customer customer = new Customer(5L, "Test", "test@example.com");
        customerService.saveCustomer(customer);


        customerService.deleteCustomer(customer.getId());

        verify(mockRepo).deleteById(customer.getId());
    }


}