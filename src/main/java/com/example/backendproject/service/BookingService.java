package com.example.backendproject.service;


import com.example.backendproject.dto.*;
import com.example.backendproject.models.Blacklist;
import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.format.jackson.JacksonJsonFormatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.lang.Character.getType;
import static java.lang.String.valueOf;


@Service
@RequiredArgsConstructor
public class BookingService {


    @Autowired
    private BookingRepo bookingRepo;

    final private RoomRepo roomRepo;
    private final CustomerRepo customerRepo;

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
    @Autowired
    private Gson gson;


    public BookingDtoDetailed bookingDtoDetailed(Booking booking) {
        return BookingDtoDetailed.builder().id(booking.getId()).checkInDate(booking.getCheckInDate()).checkOutDate(booking.getCheckOutDate())
                .customerDtoMini(new CustomerDtoMini(booking.getCustomer().getId()
                        , booking.getCustomer().getName()
                        , booking.getCustomer().getEmail())).
                roomDtoMini(new RoomDtoMini(booking.getRoom().getId()
                ,booking.getRoom().getRoomNumber()
                ,booking.getRoom().isDoubleRoom()
                ,booking.getRoom().getExtraBed()
                , booking.getRoom().getPrice())).build();
    }

    public BookingDtoMini bookingtoDtoMini(Booking booking) {
        return BookingDtoMini.builder().id(booking.getId()).checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate()).build();
    }

    public boolean findCrossedTime(LocalDate start, LocalDate stop, RoomDtoDetailed room) {
        boolean isAvaliable = false;

        if (room.getBookingDtoDetailedList().isEmpty()) {
            isAvaliable = true;
        } else {
            for (BookingDtoDetailed books : room.getBookingDtoDetailedList()) {
                //System.out.println("Check in: " + books.getCheckInDate() + "\n" +
                       // "Check out: " + books.getCheckOutDate());
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

    public BookingDtoDetailed getBookingById2(Long id) {
        Booking booking = bookingRepo.getReferenceById(id);
        return bookingDtoDetailed(booking);
    }

    public CustomerDtoMini ComparingCustomer(String email, List<CustomerDtoMini> customerList) {
       for (CustomerDtoMini customer : customerList) {
           if (Objects.equals(email, customer.getEmail())) {
               return customer;
           }
       }
       return null;
    }


    public List<BookingDtoDetailed> getAllBookingsDetailed(){
        return bookingRepo.findAll().stream().map(booking -> bookingDtoDetailed(booking)).toList();
    }

    public List<BookingDtoMini> getAllBookingsMini(){
        return bookingRepo.findAll().stream().map(booking -> bookingtoDtoMini(booking)).toList();
    }

    public List<Booking> findAllBookings() {
        return bookingRepo.findAll();
    }

    @Transactional
    public void deleteBookingById(Long id) {
        try {
            if (bookingRepo.existsById(id)) {
                bookingRepo.deleteById(id);
                logger.info("Booking with ID {} has been deleted successfully.", id);
            } else {
                logger.warn("Booking with ID {} does not exist.", id);
            }
        } catch (Exception e) {
            logger.error("An error occurred while deleting booking with ID {}: {}", id, e.getMessage());

            throw new RuntimeException("Failed to delete booking with ID " + id, e);
        }
    }

    public Optional<Booking> findBookingById(Long id) {
        return bookingRepo.findById(id);
    }

    public void updateBookingById(Long id, LocalDate newCheckInDate, LocalDate newCheckOutDate, Long roomId) {
        bookingRepo.findById(id).ifPresent(booking -> {

            Room room = roomRepo.findById(roomId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid room ID: " + roomId));

            booking.setCheckInDate(newCheckInDate);
            booking.setCheckOutDate(newCheckOutDate);
            booking.setRoom(room);

            bookingRepo.save(booking);
        });
    }
    public static boolean isBlacklisted(String email) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://javabl.systementor.se/api/grupp5/blacklistcheck/" + email))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                System.out.println(responseBody);

                return responseBody.contains("Blacklisted");
            } else {
                System.out.println("Fel vid förfrågan till den externa leverantören.");
                return false; // Returnera false som standard om det uppstår fel
            }
        } catch (Exception e) {
            System.out.println("Ett fel uppstod: " + e.getMessage());
            return false; // Returnera false om det uppstår ett undantag
        }
    }


    public List<Blacklist> getAllBlacklists() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://javabl.systementor.se/api/grupp5/blacklist"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            gson = new Gson();
            Type listType = new TypeToken<List<Blacklist>>(){}.getType();
            String responseBody = response.body();
            return gson.fromJson(responseBody, listType);
        } else {
            return new ArrayList<>();
        }
    }

    public boolean addBlacklisted(String email, boolean isOk) throws IOException, InterruptedException {
        LocalDateTime nowDate = LocalDateTime.now();
        HttpClient client = HttpClient.newHttpClient();
        Customer getCustomer = customerRepo.findByEmail(email).stream().filter(customer -> email.equals(customer.getEmail())).findFirst().orElse(null);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://javabl.systementor.se/api/grupp5/blacklist"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"email\":\"" + email + "\", \"name\":\""+getCustomer.getName()+"\", \"group\":\"grupp5\", \"created\":\"" +nowDate + "\", \"isOk\":\""+isOk+ "\"}"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() < 300;
    }

    public void BlacklistHandler(String email, boolean isNotBlacklisted) throws IOException, InterruptedException {
        if (updateBlacklistedByEmail(email, isNotBlacklisted)) return;
        addBlacklisted(email,  isNotBlacklisted);
    }

    public boolean updateBlacklistedByEmail(String email, boolean ok) throws IOException, InterruptedException{
        Customer getCustomer = customerRepo.findByEmail(email).stream().filter(customer -> email.equals(customer.getEmail())).findFirst().orElse(null);
        System.out.println("To update blacklist");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://javabl.systementor.se/api/grupp5/blacklist/" + email))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\"name\":\""+getCustomer.getName()+"\",\"isOk\":\""+ ok + "\"}"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Update successful");
        return response.statusCode() < 300;
    }

}
