package com.example.backendproject.controller;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {



    private BookingService bookingService;
    private RoomService roomService;

    @Autowired
    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }


    @GetMapping("/all")
    public @ResponseBody List<BookingDtoDetailed> allBookings(){
        return bookingService.getAllBookingsDetailed();
    }

    @GetMapping("/allmini")
    public @ResponseBody List<BookingDtoMini> allBookingsMini(){
        return bookingService.getAllBookingsMini();
    }
    @RequestMapping("/Book-A-Room")
    public String booking(){
        return "book-room.html";
    }

    @RequestMapping("/allBookings")
    public String showAllBooking(Model model){

        List<Booking> bookings = bookingService.findAllBookings();

        model.addAttribute("allaBokningar",bookings);
        return "bookings.html";
    }

    @RequestMapping("/allBookings/updateBooking/{id}")
    public String updateBooking(Model model,@PathVariable Long id){

        Optional<Booking> booking = bookingService.findBookingById(id);

        model.addAttribute("bokning",booking);

        return "update-booking.html";
    }

    @PostMapping("/allBookings/updateBooking2/{id}")
    public String handleBookingUpdate(@PathVariable Long id,
                                      @RequestParam("roomId") Long roomId,
                                      @RequestParam("startDate") String start,
                                      @RequestParam("endDate") String end,
                                      Model model) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        Optional<Booking> booking = bookingService.findBookingById(id);
        model.addAttribute("bokning", booking);

        if (!roomService.existsById(roomId)) {
            model.addAttribute("roomMsg", "Room ID: "+roomId +" finns inte.");
            return "update-booking.html";
        }
        // else if datumet är redan bokat då så går roomMsg bort och så får man ett dateErrorMsg istället


        bookingService.updateBookingById(id, startDate, endDate, roomId);


        return "redirect:/bookings/allBookings";
    }

    @PostMapping("/allBookings/delete/{id}")
    public String deleteBookingWithId(@PathVariable Long id){
        System.out.println("Deleting booking with ID: " + id);

        bookingService.deleteBookingById(id);

        return "redirect:/bookings/allBookings";
    }

    @PostMapping
    public String bookRoom(@RequestParam Room roomId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate, Model model) {
        boolean result = bookingService.createBooking(roomId, startDate, endDate);
        if (result) {
            model.addAttribute("message", "Bokning skapad!");
        } else {
            model.addAttribute("message", "Rummet är redan bokat för angivna datum.");
        }
        return "book";  // Namnet på Thymeleaf-vyn som ska renderas
    }


}
