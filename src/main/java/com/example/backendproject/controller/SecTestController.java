package com.example.backendproject.controller;

import com.example.backendproject.repo.QueueRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.UUID;



@Controller
public class SecTestController extends BaseController {
    @Autowired
    QueueRepo queueRepo;

    @GetMapping(path = "/admin")
    @PreAuthorize("hasAuthority('Admin')")
    public String empty(Model model) {

        model.addAttribute("activeFunction", "queues");
        setupVersion(model);

        model.addAttribute("page", "Admin");
        return "security/admin";
    }



    @GetMapping(path = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String Edit(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        model.addAttribute("username", username);
        return "security/profile";
    }

}
