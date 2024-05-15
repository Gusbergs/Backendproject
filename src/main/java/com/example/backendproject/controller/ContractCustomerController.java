package com.example.backendproject.controller;

import com.example.backendproject.dto.ContractCustomersDtoMini;
import com.example.backendproject.models.ContractCustomer;
import com.example.backendproject.service.ContractCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/getAllContractCustomersInDetail/{id}")
    public String getContractCustomerDetail(@PathVariable Long id, Model model) {
        Optional<ContractCustomer> contractCustomerOptional = customerService.getContractCustomerById(id);
        ContractCustomer contractCustomer = contractCustomerOptional.orElseThrow(() -> new RuntimeException("Contract customer not found")); // or handle the case where the customer is not found

        model.addAttribute("contractCustomer", contractCustomer);
        return "detailed-contract-customers"; // Assuming the template name is detailed-contract-customers.html
    }
}
