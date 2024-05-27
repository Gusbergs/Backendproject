package com.example.backendproject.controller;


import com.example.backendproject.dto.BookingDtoDetailed;
import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.CustomerDtoMini;
import com.example.backendproject.dto.RoomDtoDetailed;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import com.example.backendproject.service.BookingService;
import com.example.backendproject.service.CustomerService;
import com.example.backendproject.service.DiscountService;
import com.example.backendproject.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private final DiscountService discountService;

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
                                    @RequestParam String email,
                                    Model model) {

        if (bookingService.isBlacklisted(email)) {
            System.out.println("Personen är blacklistad och kan inte boka hos oss.");
            model.addAttribute("error_message", "Customer is blacklisted");
            model.addAttribute("isAvailable", false);
            return "book-room.html";
        }

        if (!customerService.checkIfCustomerExist(email)){
            model.addAttribute("error_message", "Customer dose not exist");
            model.addAttribute("isAvailable", false);
            return "book-room.html";
        }

        List<RoomDtoDetailed> roomList = roomService.getAllRoomsDetailed();
        List<CustomerDtoMini> customerList = customerService.getAllCustomersMini();
        RoomDtoDetailed comparingRoom = null;
        for (RoomDtoDetailed rooms : roomList) {
            if (roomId == rooms.getId()) {
                comparingRoom = rooms;
                break;
            }
        }

        CustomerDtoMini comparedCustomer = bookingService.ComparingCustomer(email, customerList);

        if (comparingRoom == null) {
            model.addAttribute("error_message", "Rumsnumret finns inte");
            model.addAttribute("isAvailable", false);
            return "book-room.html";
        } else if (comparedCustomer == null) {
            model.addAttribute("error_message", "Kunden finns inte i systemet");
            model.addAttribute("isAvailable", false);
            return "book-room.html";
        } else if (!bookingService.findCrossedTime(startDate, endDate, comparingRoom)) {
            model.addAttribute("error_message", "Bokningsperioden är redan bokad");
            model.addAttribute("isAvailable", bookingService.findCrossedTime(startDate, endDate, comparingRoom));
            return "book-room.html";
        } else {
            Room bookedRoom = roomRepo.getReferenceById(roomId);
            Customer bookedCustomer = customerRepo.getReferenceByEmail(email);
            Booking newBooking = new Booking(startDate, endDate, bookedRoom, bookedCustomer);
            bookingRepo.save(newBooking);

            double price = newBooking.getRoom().getPrice();
            double discountPrice = price * discountService.getDiscount(email, newBooking.getId());

            System.out.println(price);
            System.out.println(discountPrice);


            model.addAttribute("source", "addNewBooking");
            model.addAttribute("newBooking", bookingService.findBookingById(newBooking.getId()));
            model.addAttribute("Price", price);
            model.addAttribute("discountPrice", discountPrice);
            return "confirm-booked-room.html";
        }
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
        System.out.println(roomRepo.getReferenceById(roomId).getRoomNumber());
        RoomDtoDetailed comparingRoom = roomService.roomDtoDetailed(roomRepo.getReferenceById(roomId));
        model.addAttribute("bokning", booking);

        if (!roomService.existsById(roomId)) {
            model.addAttribute("roomMsg", "Room ID: "+roomId +" finns inte.");
            return "update-booking.html";
        } else if (!bookingService.findCrossedTime(startDate, endDate, comparingRoom)) {
            model.addAttribute("dateMsg", "Bokningsperioden är redan bokad");
            model.addAttribute("isAvailable", bookingService.findCrossedTime(startDate, endDate, comparingRoom));
            return "update-booking.html";
        }



        bookingService.updateBookingById(id, startDate, endDate, roomId);
        model.addAttribute("source", "updateBooking");
        model.addAttribute("newBooking", bookingService.findBookingById(id));

        //return "redirect:/bookings/allBookings";
        return "confirm-booked-room.html";
    }

    @PostMapping("/allBookings/delete/{id}")
    public String deleteBookingWithId(@PathVariable Long id, RedirectAttributes redirectAttributes){
        System.out.println("Deleting booking with ID: " + id);

        bookingService.deleteBookingById(id);

        redirectAttributes.addFlashAttribute("deleteBookingMsg", "Bokningen med id:"+id + " blev raderad.");
        redirectAttributes.addFlashAttribute("msgType", "danger");

        return "redirect:/bookings/allBookings";
    }


}
