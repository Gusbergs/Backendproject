package com.example.backendproject.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class XmlStreamProvider {

    public InputStream getContractCustomerDataStream() throws IOException {
        URL ccUrl = new URL("https://javaintegration.systementor.se/customers");
        return ccUrl.openStream();
    }

}
