package com.example.backendproject.fetch;

import com.example.backendproject.service.JsonStreamProvider;
import com.example.backendproject.service.ShipperService;
import com.example.backendproject.service.XmlStreamProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class FetchShippersTest {

    @Autowired
    JsonStreamProvider jsonStreamProvider;

    private JsonStreamProvider mockJsonStreamProvider = mock(JsonStreamProvider.class);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getFetchShippers() throws IOException {
        Scanner scanner = new Scanner(jsonStreamProvider.getShipperDataStream()).useDelimiter("\\A");
        String result = scanner.hasNext() ? scanner.next() : "";

        System.out.println(result);

        assertTrue(result.contains("id"));
        assertTrue(result.contains("email"));
        assertTrue(result.contains("companyName"));
        assertTrue(result.contains("contactName"));
        assertTrue(result.contains("contactTitle"));
        assertTrue(result.contains("streetAddress"));
        assertTrue(result.contains("city"));
        assertTrue(result.contains("postalCode"));
        assertTrue(result.contains("country"));
        assertTrue(result.contains("phone"));
        assertTrue(result.contains("fax"));

    }
}