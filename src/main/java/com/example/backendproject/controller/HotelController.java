package com.example.backendproject.controller;

import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import com.example.backendproject.models.Customer;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HotelController {

    private final CustomerRepo customerRepo;

    private final CustomerService customerService;

    private final BookingService bookingService;
    /*@RequestMapping("/addCustomer")
    public String addingCustomer(){
        return "register-customer.html";
    }

    @GetMapping("/Book-A-Room")
    public String booking(){
        return "book-room.html";
    }

    @GetMapping("/Register-customer")
    public String registerCustomer(){
        return "register-customer.html";
    }

    @GetMapping("/Search-customer")
    public String searchCustomerById() {
        return "search-customer-email.html";
    }




    @GetMapping("/Search-customer/delete/{booking}&{customer}")
    public String deleteCustomer(@PathVariable("booking") BookingDtoDetailed booking,
                                 @PathVariable("customer") CustomerDtoDetailed customer, Model model) {
        bookingService.deleteBooking(booking);
        return showCustomerByEmail(customer.getEmail(), model);
    }
    @PostMapping("/Search-customer")
    public String showCustomerByEmail(@RequestParam String epost, Model model) {
        boolean isExist = false;
        CustomerDtoDetailed customer = null;
        for (int i = 0; i < customerService.getAllCustomersDetailed().size(); i++) {
            if (epost.equalsIgnoreCase(customerService.getAllCustomersDetailed().get(i).getEmail())) {
                customer = customerService.getAllCustomersDetailed().get(i);
                isExist = true;
                break;
            }
        }
        if (isExist) {

            model.addAttribute("customer_name", customer.getName());
            model.addAttribute("customer_email", customer.getEmail());
            model.addAttribute("customer_booking_room", customer.getBookingDtoList());
            /*model.addAttribute("customer_booking_room", customer.getBookingDtoList()
                    .stream().map(bookingDtoDetailed -> bookingDtoDetailed.getRoomDtoMini()).toList());
        } else {
            String message = "No info is found";
            model.addAttribute("message", message);

        }
        model.addAttribute("isExist", isExist);
        return "search-customer-email.html";
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

    }*/

}
