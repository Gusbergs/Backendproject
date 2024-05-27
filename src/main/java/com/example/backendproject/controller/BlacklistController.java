package com.example.backendproject.controller;

import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.models.Blacklist;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor

@RequestMapping("/blacklists")
@PreAuthorize("hasAuthority('Admin')")
public class BlacklistController {

    private final BookingService bookingService;
    private final CustomerService customerService;

    @RequestMapping("")
    public String blacklistAlt(Model model) {
        model.addAttribute("command", "blacklistAlt");
        return "blacklist.html";
    }

    @RequestMapping("/addNewBlacklists")
    public String addNewBlacklistsSite(Model model) throws IOException, InterruptedException{
        model.addAttribute("command", "addNewBlacklist");
        return "blacklist.html";
    }

    @PostMapping("/addNewBlacklists/success")
    public String addNewBlacklistSuccess(@RequestParam String email
            , @RequestParam String isOk
            , Model model)
            throws IOException, InterruptedException {
        System.out.println("adding");
        System.out.println("isOk: " + isOk);
        //model.addAttribute("command", "addNewBlacklistSuccession");
        if (email.isEmpty() || Objects.equals(isOk, "Choose")){
            model.addAttribute("command", "addNewBlacklist");
            model.addAttribute("error_message", "Vänligen skriver email eller väljer blacklist alternativ");
            model.addAttribute("isAvailable", false);
            return "blacklist.html";
        } else if (customerService.checkIfCustomerExist(email)) {
            boolean ok = Boolean.parseBoolean(isOk);
            bookingService.BlacklistHandler(email, ok);
            System.out.println("Add new is success");
            return "redirect:/blacklists";
        } else {
            model.addAttribute("command", "addNewBlacklist");
            model.addAttribute("error_message", "Kunden finns inte i systemet");
            model.addAttribute("isAvailable", false);
            System.out.println("Add new is failed");
            return "blacklist.html";
        }
    }

    @GetMapping("/getBlacklists")
    public String showAllBlacklists(Model model) throws IOException, InterruptedException {
        List<Blacklist> blacklists = bookingService.getAllBlacklists();
        model.addAttribute("command", "getAllblacklists");
        model.addAttribute("blacklists", blacklists);
        return "blacklist.html";
    }


}
