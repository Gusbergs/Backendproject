package com.example.backendproject.service;

import com.example.backendproject.dto.ShipperDtoMini;
import com.example.backendproject.models.Shipper;
import com.example.backendproject.repo.ShipperRepo;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShipperServiceTest {

    @MockBean
    ShipperRepo shipperRepo;

    private final JsonStreamProvider jsonStreamProvider = mock(JsonStreamProvider.class);

    @Autowired
    @InjectMocks
    ShipperService service;
    @BeforeEach
    void setUp() throws Exception {

        service = new ShipperService(shipperRepo);

        InputStream jsonStream = getClass().getClassLoader().getResourceAsStream("shipper.json");
        when(jsonStreamProvider.getShipperDataStream()).thenReturn(jsonStream);


        JsonMapper mapper = new JsonMapper();
        Shipper[] shippers = mapper.readValue(jsonStream, Shipper[].class);
        List<Shipper> shipperLists = Arrays.asList(shippers);
        //System.out.println(shipperLists.get(0).getCompanyName());
        when(shipperRepo.saveAll(anyList())).thenReturn(shipperLists);
        when(shipperRepo.findAll()).thenReturn(shipperLists);
        //System.out.println(shipperRepo.findAll());
    }
    @Test
    void shipperDtoMini() throws Exception {
        Shipper shipper1 = new Shipper(8L, "anna.gustafsson@yahoo.com", "Johansson-Änglund", "Nils Karlsson", "engineer", "Nygränden 012", "Kungborg", "50057", "Sverige", "070-136-6555", "4204-173499");
        ShipperDtoMini  shipperDto = service.shipperDtoMini(shipper1);

        assertTrue(shipperDto.getCompanyName().equals("Johansson-Änglund"));
        assertTrue(shipperDto.getId() == 8L);
        assertTrue(shipperDto.getPhone().equals("070-136-6555"));
    }

    @Test
    void getAllShippersDtoMini() {


        List<ShipperDtoMini> shipperList = service.getAllShippersDtoMini();
        System.out.println("FindAll: "+shipperRepo.findAll());
        System.out.println("Result of ShipperTest: "+shipperList);

        assertNotNull(shipperList);
        assertFalse(shipperList.isEmpty());
        assertEquals(1, shipperList.size());
        assertEquals("Johansson-Änglund", shipperList.get(0).getCompanyName());
        assertEquals(8L, shipperList.get(0).getId());
        assertEquals("070-136-6555" ,shipperList.get(0).getPhone());
    }

    @Test
    void saveShipper() {
        Shipper shipper1 = new Shipper(1L, "anna.gustafsson@yahoo.com", "Johansson-Änglund", "Nils Karlsson", "engineer", "Nygränden 012", "Kungborg", "50057", "Sverige", "070-136-6555", "4204-173499");
        service.saveShipper(shipper1);

        assertTrue(shipperRepo.findAll().stream().anyMatch(shipper -> shipper.companyName.equals("Johansson-Änglund")));
    }
}