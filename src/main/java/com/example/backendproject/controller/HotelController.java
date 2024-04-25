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

    @RequestMapping("/addCustomer")
    public String addingCustomer(){
        return "register-customer.html";
    }

    @RequestMapping("/Book-A-Room")
    public String booking(){
        return "book-room.html";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestParam String name,
                              @RequestParam String email, Model model){


        boolean customerExists = customerRepo.findByEmail(email).isPresent();
        if (customerExists) {
            model.addAttribute("error", "A customer with this email already exists.");
            model.addAttribute("msg", "Detta e-postmeddelande finns redan.");
            model.addAttribute("msgType", "danger");
            return "register-customer.html";
        }else {

        model.addAttribute("name", name);
        model.addAttribute("email", email);

        customerRepo.save(new Customer(name,email));
        model.addAttribute("msg", "Ny användare " + name + " har lagts till.");
        model.addAttribute("msgType", "success");
        return "register-customer.html";
        }

    }

}
