package com.example.backendproject.controller;

import com.example.backendproject.dto.RoomDtoMini;
import com.example.backendproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public String getAllRooms(Model model) {
        List<RoomDtoMini> rooms = roomService.getAllRoomsMini();
        model.addAttribute("rooms", rooms);
        return "rooms"; // This should match the name of your HTML template file without the extension
    }
}
