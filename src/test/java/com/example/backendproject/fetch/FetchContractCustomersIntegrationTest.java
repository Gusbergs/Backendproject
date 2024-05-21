package com.example.backendproject.fetch;

import com.example.backendproject.service.XmlStreamProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static junit.framework.TestCase.assertTrue;

@SpringBootTest
public class FetchContractCustomersIntegrationTest {

    @Autowired
    XmlStreamProvider xmlStreamProvider;


    @Test
    void FetchContractCustomersWillFetch() throws IOException {

        InputStream inputStream = xmlStreamProvider.getDataStream();


        String result = new Scanner(inputStream).useDelimiter("\\A").next();

        assertTrue(  result.contains("<allcustomers>") );
        assertTrue(  result.contains("</allcustomers>") );
        assertTrue(  result.contains("<customers>") );
        assertTrue(  result.contains("</customers>") );
        assertTrue(  result.contains("<id>") );
        assertTrue(  result.contains("</id>") );
        assertTrue(  result.contains("<companyName>") );
        assertTrue(  result.contains("</companyName>") );
        assertTrue(  result.contains("<contactName>") );
        assertTrue(  result.contains("</contactName>") );
        assertTrue(  result.contains("<contactTitle>") );
        assertTrue(  result.contains("</contactTitle>") );
        assertTrue(  result.contains("<streetAddress>") );
        assertTrue(  result.contains("</streetAddress>") );
        assertTrue(  result.contains("<city>") );
        assertTrue(  result.contains("</city>") );
        assertTrue(  result.contains("<postalCode>") );
        assertTrue(  result.contains("</postalCode>") );
        assertTrue(  result.contains("<country>") );
        assertTrue(  result.contains("</country>") );
        assertTrue(  result.contains("<phone>") );
        assertTrue(  result.contains("</phone>") );
        assertTrue(  result.contains("<fax>") );
        assertTrue(  result.contains("</fax>") );

    }



}
