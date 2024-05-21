package com.example.backendproject.controller;

import com.example.backendproject.dto.ContractCustomersDtoMini;
import com.example.backendproject.models.ContractCustomer;
import com.example.backendproject.service.ContractCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ContractCustomers")
@RequiredArgsConstructor
public class ContractCustomerController {

    final ContractCustomerService customerService;

    @GetMapping("/getAllContractCustomers")
    public String getContractCustomersMini(Model model,
                                           @RequestParam(defaultValue = "1") int pageNo,
                                           @RequestParam(defaultValue = "15") int pageSize,
                                           @RequestParam(defaultValue = "contactName") String sortCol,
                                           @RequestParam(defaultValue = "ASC") String sortOrder,
                                           @RequestParam(defaultValue = "") String q) {

        q = q.trim();
        model.addAttribute("q", q);

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortCol);
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);

        if (!q.isEmpty()) {
            Page<ContractCustomersDtoMini>  allContractCustomers = customerService.findAllByCountryOrContactNameOrCompanyNameContains(q, q, q, pageable);
            model.addAttribute("contractCustomer", allContractCustomers);
            model.addAttribute("totalPages", allContractCustomers.getTotalPages());
            model.addAttribute("pageNo", pageNo);
        } else {
            Page<ContractCustomersDtoMini>  allContractCustomers = customerService.getAllContractCustomersDtoMiniPageable(pageable);
            model.addAttribute("contractCustomer", allContractCustomers);
            model.addAttribute("totalPages", allContractCustomers.getTotalPages());
            model.addAttribute("pageNo", pageNo);
        }
        return "contract-customers.html";
    }
    @GetMapping("/getAllContractCustomersInDetail/{id}")
    public String getContractCustomerDetail(@PathVariable Long id, Model model) {
        Optional<ContractCustomer> contractCustomerOptional = customerService.getContractCustomerById(id);
        ContractCustomer contractCustomer = contractCustomerOptional.orElseThrow(() -> new RuntimeException("Contract customer not found"));

        model.addAttribute("contractCustomer", contractCustomer);
        return "detailed-contract-customers";
    }
}
