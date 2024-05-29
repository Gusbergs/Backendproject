package com.example.backendproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogInController {
    @GetMapping("/custom-login")
    public String customLogin() {
        return "custom-login.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "perform-logout.html";
    }

    @RequestMapping("/perform-logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/custom-login"; // Redirect to the custom login page
    }
}
