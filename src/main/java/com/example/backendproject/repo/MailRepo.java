package com.example.backendproject.repo;

import com.example.backendproject.models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepo extends JpaRepository<Mail,Long> {
}
