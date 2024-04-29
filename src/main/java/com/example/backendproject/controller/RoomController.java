package com.example.backendproject.controller;


import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Bookings")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService=roomService;
    }

   /* @RequestMapping("/allRooms")
    public @ResponseBody List<RoomDtoDetailed>  allRoomsDetil

    */




}
