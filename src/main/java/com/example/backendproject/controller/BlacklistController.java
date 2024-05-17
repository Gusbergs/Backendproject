package com.example.backendproject.controller;

import com.example.backendproject.dto.CustomerDtoDetailed;
import com.example.backendproject.models.Blacklist;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blacklists")
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
            , @RequestParam boolean isOk
            , Model model)
            throws IOException, InterruptedException {
        System.out.println("adding");
        //model.addAttribute("command", "addNewBlacklistSuccession");
        if (customerService.checkIfCustomerExist(email)) {
            bookingService.BlacklistHandler(email, isOk);
            System.out.println("Add new is success");
            return "redirect:/blacklists";
        } else {
            model.addAttribute("command", "blacklistAlt");
            model.addAttribute("command, addNewBookingFailed");
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
