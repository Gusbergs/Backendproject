package com.example.backendproject;

import com.example.backendproject.models.AllContractCustomers;
import com.example.backendproject.models.ContractCustomer;
import com.example.backendproject.service.ContractCustomerService;
import com.example.backendproject.service.XmlStreamProvider;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.io.InputStream;
import java.net.URL;

@ComponentScan
@RequiredArgsConstructor
public class FetchContractCustomers implements CommandLineRunner {

    final ContractCustomerService customerService;
    private final XmlStreamProvider ccXmlProvider;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Kör fetcbcustomers");

        JacksonXmlModule module = new JacksonXmlModule();

        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        InputStream getContractCustomer = ccXmlProvider.getContractCustomerDataStream();
        AllContractCustomers allContractCustomers = xmlMapper.readValue(new URL("https://javaintegration.systementor.se/customers"),
                AllContractCustomers.class
        );

        for (ContractCustomer s : allContractCustomers.customers) {
            System.out.println(s.contractCustomerId + "contractCustomerId");
            customerService.saveContractCustomer(s);
        }
    }
}
