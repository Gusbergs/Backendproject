package com.example.backendproject.controller;

import com.example.backendproject.models.User;
import com.example.backendproject.repo.UserRepo;
import com.example.backendproject.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PasswordResetController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "Forgot-Password.html";
    }

    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam("email") String email, Model model) {
        User user = userRepository.findByUsername(email);
        if (user != null) {
            passwordResetService.sendPasswordResetEmail(email);
            return "redirect:/forgot-password?success";
        } else {
            model.addAttribute("error_message", "E-posten finns inte");
            model.addAttribute("isAvailable", false);
            return "Forgot-Password.html";
        }
    }

    @GetMapping("/reset-password")
    public ModelAndView showResetPasswordPage(@RequestParam("token") String token) {
        ModelAndView modelAndView = new ModelAndView("reset-password");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @PostMapping("/reset-password")
    public String handleResetPassword(@RequestParam("token") String token, @RequestParam("password") String password) {
        User user = userRepository.findByResetToken(token);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(password));
            user.setResetToken(null);
            userRepository.save(user);
            return "redirect:/login?resetSuccess";
        }
        return "redirect:/reset-password?error";
    }
}
