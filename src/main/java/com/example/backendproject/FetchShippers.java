package com.example.backendproject;

import com.example.backendproject.models.Shipper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;

@ComponentScan
public class FetchShippers implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Kör fetcbcustomers");

        JsonMapper jsonMapper = new JsonMapper();
        Shipper[] shippers = jsonMapper.readValue(new URL("https://javaintegration.systementor.se/shippers"),
                Shipper[].class
        );

        for (Shipper p : shippers) {
            System.out.println(p.companyName);
        }
    }
}
