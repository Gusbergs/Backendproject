package com.example.backendproject;

import com.example.backendproject.models.Shipper;
import com.example.backendproject.repo.ShipperRepo;
import com.example.backendproject.service.JsonStreamProvider;
import com.example.backendproject.service.ShipperService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.io.InputStream;
import java.net.URL;

@ComponentScan
@RequiredArgsConstructor
public class FetchShippers implements CommandLineRunner {
    private final JsonStreamProvider jsonStreamProvider;
    private final ShipperService shipperService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Kör fetcbcustomers");

        JsonMapper jsonMapper = new JsonMapper();
        InputStream inputStream = jsonStreamProvider.getShipperDataStream();
        Shipper[] shippers = jsonMapper.readValue(inputStream,
                Shipper[].class
        );
        /*Shipper[] shippers = jsonMapper.readValue(new URL("https://javaintegration.systementor.se/shippers"),
                Shipper[].class
        );*/

        for (Shipper p : shippers) {
            shipperService.saveShipper(p);
            System.out.println(p.companyName);
        }
    }
}
