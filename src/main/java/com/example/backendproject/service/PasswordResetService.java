package com.example.backendproject.service;


import com.example.backendproject.models.User;
import com.example.backendproject.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String email) {
        User user = userRepository.findByUsername(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            userRepository.save(user);

            SimpleMailMessage emailMessage = new SimpleMailMessage();
            emailMessage.setTo(user.getUsername());
            emailMessage.setSubject("Password Reset Request");
            emailMessage.setText("To reset your password, click the link below:\n" +
                    "http://localhost:8080/reset-password?token=" + token);
            mailSender.send(emailMessage);
        }
    }
}
