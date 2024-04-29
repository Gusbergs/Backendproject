package com.example.backendproject.controller;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.models.Customer;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Customers")
public class CustomerController {



    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/all")
    public @ResponseBody List<CustomerDtoDetailed> allCustomersDetailed(){
        return customerService.getAllCustomersDetailed();
    }

    @GetMapping("/allmini")
    public @ResponseBody List<CustomerDtoMini> allCustomersMini(){
        return customerService.getAllCustomersMini();
    }
    @RequestMapping("/addCustomer")
    public String addingCustomer(){
        return "register-customer.html";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestParam String name,
                              @RequestParam String email, Model model){


        if (customerService.checkIfCustomerExist(email)) {
            model.addAttribute("msg", "Denna användare finns redan.");
            model.addAttribute("msgType", "danger");
            return "register-customer.html";
        }else {

            model.addAttribute("name", name);
            model.addAttribute("email", email);

            customerService.saveCustomer(new Customer(name, email));
            model.addAttribute("msg", "Ny användare " + name + " har lagts till.");
            model.addAttribute("msgType", "success");
            return "register-customer.html";
        }
    }


}
