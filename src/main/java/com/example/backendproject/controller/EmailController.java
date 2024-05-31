package com.example.backendproject.controller;

import com.example.backendproject.models.Mail;
import com.example.backendproject.repo.MailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Email")
@RequiredArgsConstructor
public class EmailController {

    private final MailRepo mailRepo;
    @RequestMapping("")
    public String customEmail(Model model) {
        Mail mail = mailRepo.getReferenceById(1L);
        model.addAttribute("mail", mail);  // Lägg till mail-objektet som ett attribut i modellen
        return "custom-email";  // Returnera namnet på HTML-filen utan .html
    }

    @PostMapping("/update")
    public String UpdateEmail(@RequestParam String subject,
                              @RequestParam String text, Model model){

        Mail mail = mailRepo.getReferenceById(1L);

        mail.setSubject(subject);
        mail.setText(text);

        mailRepo.save(mail);
        model.addAttribute("msg", "Email:n har uppdaterats");
        model.addAttribute("mail",mail);

        return "custom-email";
    }
}