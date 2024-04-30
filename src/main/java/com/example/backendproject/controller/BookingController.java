package com.example.backendproject.controller;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
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
@RequiredArgsConstructor
public class BookingController {



    private final BookingService bookingService;
    private final RoomService roomService;

    private final CustomerRepo customerRepo;
    private final RoomRepo roomRepo;

    private final BookingRepo bookingRepo;
    private final CustomerService customerService;

    /*@Autowired
    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }*/


    @GetMapping("/all")
    public @ResponseBody List<BookingDtoDetailed> allBookings(){
        return bookingService.getAllBookingsDetailed();
    }

    @GetMapping("/allmini")
    public @ResponseBody List<BookingDtoMini> allBookingsMini(){
        return bookingService.getAllBookingsMini();
    }
    @GetMapping("/Book-A-Room")
    public String booking(){
        return "book-room.html";
    }

    @PostMapping("/Book-A-Room")
    public String bookingSuccession(@RequestParam LocalDate startDate,
                                    @RequestParam LocalDate endDate,
                                    @RequestParam Long roomId,
                                    @RequestParam Long customerId,
                                    Model model) {
        List<RoomDtoDetailed> roomList = roomService.getAllRoomsDetailed();
        RoomDtoDetailed comparingRoom = null;
        for (RoomDtoDetailed rooms : roomList) {
            if (roomId == rooms.getId()) {
                comparingRoom = rooms;
                break;
            }
        }
        if (!findCrossedTime(startDate, endDate, comparingRoom)) {
            model.addAttribute("isAvailable", findCrossedTime(startDate, endDate, comparingRoom));
            return "book-room.html";
        } else {
            Room bookedRoom = roomRepo.getReferenceById(roomId);
            Customer bookedCustomer = customerRepo.getReferenceById(customerId);
            bookingRepo.save(new Booking(startDate, endDate, bookedRoom, bookedCustomer));
            return "book-room.html";
        }
    }
    
    public boolean findCrossedTime(LocalDate start, LocalDate stop, RoomDtoDetailed room) {
        boolean isAvaliable = false;

        if (room.getBookingDtoDetailedList().isEmpty()) {
            isAvaliable = true;
        } else {
            for (BookingDtoDetailed books : room.getBookingDtoDetailedList()) {
                System.out.println("Check in: " + books.getCheckInDate() + "\n" +
                        "Check out: " + books.getCheckOutDate());
                if (books.getCheckInDate().isAfter(start) && books.getCheckInDate().isAfter(stop)) {
                    isAvaliable = true;
                } else if (books.getCheckOutDate().isBefore(start) && books.getCheckOutDate().isBefore(stop)) {
                    isAvaliable = true;
                } else {
                    isAvaliable = false;
                    break;
                }
            }
        }

        return isAvaliable;
    }

    @GetMapping("/allBookings")
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


}
