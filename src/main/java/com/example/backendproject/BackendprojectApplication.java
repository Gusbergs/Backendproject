package com.example.backendproject;

import com.example.backendproject.models.Booking;
import com.example.backendproject.models.Customer;
import com.example.backendproject.models.Room;
import com.example.backendproject.repo.BookingRepo;
import com.example.backendproject.repo.CustomerRepo;
import com.example.backendproject.repo.RoomRepo;
import com.example.backendproject.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class BackendprojectApplication {




	public static void main(String[] args) {



		if(args.length == 0) {
			SpringApplication.run(BackendprojectApplication.class, args);

		}else if(Objects.equals(args[0], "FetchContractCustomers")){
			SpringApplication application = new SpringApplication(FetchContractCustomers.class);
			application.setWebApplicationType(WebApplicationType.NONE);
			application.run(args);

		}else if(Objects.equals(args[0], "FetchShippers")) {
			SpringApplication application = new SpringApplication(FetchShippers.class);
			application.setWebApplicationType(WebApplicationType.NONE);
			application.run(args);
		}
		else if(Objects.equals(args[0], "ReadQueueApp")){
				SpringApplication application = new SpringApplication(ReadQueueApp.class);
				application.setWebApplicationType(WebApplicationType.NONE);
				application.run(args);

			}


	}

/*	@Bean
	public CommandLineRunner demo(BookingRepo bookingRepo, CustomerRepo customerRepo, RoomRepo roomRepo) {

		return (args) -> {

			LocalDate i1 = LocalDate.of(1999, 12, 11);
			LocalDate i2 = LocalDate.of(2021, 1, 11);
			LocalDate i3 = LocalDate.of(2021, 10, 1);
			LocalDate o1 = LocalDate.of(2000, 4, 3);
			LocalDate o2 = LocalDate.of(2021, 5, 2);
			LocalDate o3 = LocalDate.of(2022, 11, 1);

			Room r1 = new Room(222, false, 0, 399);
			Room r2 = new Room(111, false, 1, 499);
			Room r3 = new Room(333, true, 2, 599);
			Room r4 = new Room(444, true, 2, 699);
			Room r5 = new Room(555, true, 0, 799);
			Room r6 = new Room(666, false, 1, 899);


			Customer c1 = new Customer("kalle ", "Hej@kalle.123");
			Customer c2 = new Customer("Sven ", "Hej@Sven.123");
			Customer c3 = new Customer("Filip ", "Hej@Filip.123");


			Booking booking1 = new Booking(i1, o1, r1, c1);
			Booking booking2 = new Booking(i2, o2, r2, c2);
			Booking booking3 = new Booking(i3, o3, r3, c3);

			roomRepo.save(r1);
			roomRepo.save(r2);
			roomRepo.save(r3);
			roomRepo.save(r4);
			roomRepo.save(r5);
			roomRepo.save(r6);


			customerRepo.save(c1);
			customerRepo.save(c2);
			customerRepo.save(c3);


			bookingRepo.save(booking1);
			bookingRepo.save(booking2);
			bookingRepo.save(booking3);

		};
	}


 */







}
