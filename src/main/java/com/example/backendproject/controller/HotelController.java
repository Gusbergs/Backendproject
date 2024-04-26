package com.example.backendproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import com.example.backendproject.models.Customer;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HotelController {

    private final CustomerRepo customerRepo;





}
