package com.example.backendproject;

import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class BackendprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendprojectApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookingRepo bookingRepo, CustomerRepo customerRepo, RoomRepo roomRepo) {
		return (args) -> {
			Customer newCustomer = new Customer("Krits", "Krits@123.345");
			Room room = new Room(1234, true, 2);
			customerRepo.save(newCustomer);
			roomRepo.save(room);

			LocalDate startDate = LocalDate.now();
			LocalDate stopDate = LocalDate.of(2024,4,28);
			Booking newBooking = new Booking(startDate, stopDate, room, newCustomer);
			bookingRepo.save(newBooking);
		};
	}

}
