package com.example.backendproject.service;

import com.example.backendproject.FetchShippers;
import com.example.backendproject.dto.ShipperDtoMini;
import com.example.backendproject.models.Shipper;
import com.example.backendproject.repo.ShipperRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShipperServiceTest {

    @MockBean
    ShipperRepo shipperRepo;

    private JsonStreamProvider jsonStreamProvider = mock(JsonStreamProvider.class);
    private FetchShippers fetchShippers = mock(FetchShippers.class);
    ShipperService service;
    @BeforeEach
    void setUp() throws Exception {
        fetchShippers.run();
        service = new ShipperService(shipperRepo);
        when(jsonStreamProvider.getShipperDataStream()).thenReturn(getClass()
                .getClassLoader().getResourceAsStream("shipper.json"));
    }

    @Test
    void shipperDtoMini() {
        List<ShipperDtoMini> shipperList = service.getAllShippersDtoMini();
        System.out.println("Result of ShipperTest: "+shipperList);
    }

    @Test
    void getAllShippersDtoMini() {
    }

    @Test
    void saveShipper() {
    }
}