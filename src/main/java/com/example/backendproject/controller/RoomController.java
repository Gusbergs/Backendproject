package com.example.backendproject.controller;

import com.example.backendproject.dto.RoomDtoMini;
import com.example.backendproject.models.EventViewModel;
import com.example.backendproject.models.QueueModel;




import com.example.backendproject.repo.QueueRepository;
import com.example.backendproject.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Room")
public class RoomController {

    private final RoomService roomService;
    private final QueueRepository queueRepository;

    @Autowired
    public RoomController(RoomService roomService, QueueRepository queueRepository) {
        this.roomService = roomService;
        this.queueRepository = queueRepository;
    }





    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        List<RoomDtoMini> rooms = roomService.getAllRoomsMini();
        model.addAttribute("rooms", rooms);
        return "rooms.html";
    }

    @GetMapping("/rooms/queue/{id}")
    public String getRoomQueue(@PathVariable Long id, Model model) {
        Optional<QueueModel> queue = roomService.getQueueModelById(id);
        model.addAttribute("queue", queue);
        return "queue.html";
    }









}
