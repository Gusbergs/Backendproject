package com.example.backendproject.controller;


import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.dto.UserDtoMini;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.User;
import com.example.backendproject.repo.UserRepo;
import com.example.backendproject.service.CustomerService;
import com.example.backendproject.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("User")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('Admin')")
public class UserController {



    private final UserRepo userRepo;

    private final UserDetailsServiceImpl userDetailsService;







    @GetMapping("/addUser")
    public String allCustomers(Model model) {
        List<UserDtoMini> user = userDetailsService.userDtoConvertToIterabel(userRepo.findAll());
        model.addAttribute("user", user);
        return "user.html";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String email,
                              @RequestParam String password,
                              @RequestParam String role,
                              Model model){


        if (userDetailsService.checkIfUserExist(email)) {
            model.addAttribute("msg", "Denna User finns redan.");
            model.addAttribute("msgType", "danger");
            return "user.html";
        }else {

            model.addAttribute("email", email);
            model.addAttribute("password", password);
            model.addAttribute("role", role);


            userDetailsService.addUserWithPassword(email, password, role);
            model.addAttribute("msg", "Ny användare " + email + " har lagts till.");
            model.addAttribute("msgType", "success");
            return "redirect:/User/addUser";
        }
    }

    @PostMapping("/delete/{userId}")
    public String deleteUsername(@PathVariable UUID userId, RedirectAttributes redirectAttributes) {
        userRepo.deleteById(userId);
            redirectAttributes.addFlashAttribute("deleteCustomerMsg", "User har tagits bort.");
            redirectAttributes.addFlashAttribute("msgType2", "success");

        return "redirect:/User/addUser";
    }



}
