package com.example.backendproject.controller;


import com.example.backendproject.dto.*;
import com.example.backendproject.models.Customer;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Customers")
@RequiredArgsConstructor
public class CustomerController {



    private final CustomerService customerService;






    @GetMapping("/addCustomer")
    public String allCustomers(Model model) {
        List<CustomerDtoMini> customers = customerService.getAllCustomersMini();
        model.addAttribute("customers", customers);
        return "register-customer.html";
    }

    @GetMapping("/allmini")
    public @ResponseBody List<CustomerDtoMini> allCustomersMini(){
        return customerService.getAllCustomersMini();
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
            return "redirect:/Customers/addCustomer";
        }
    }
    @PostMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/Customers/addCustomer";
    }

}
