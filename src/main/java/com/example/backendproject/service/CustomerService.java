package com.example.backendproject.service;


import com.example.backendproject.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {


    final private CustomerRepo customerRepo;



}
