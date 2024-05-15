package com.example.backendproject.controller;

import com.example.backendproject.dto.ContractCustomersDtoMini;
import com.example.backendproject.models.ContractCustomer;
import com.example.backendproject.service.ContractCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ContractCustomers")
@RequiredArgsConstructor
public class ContractCustomerController {
    final ContractCustomerService customerService;

    @GetMapping("/getAllContractCustomers")
    public String getContractCustomersMini(Model model) {

        List<ContractCustomersDtoMini> allContractCustomers = customerService.getAllContractCustomersDtoMini();
        model.addAttribute("contractCustomer", allContractCustomers);
        return "contract-customers.html";
    }
}
