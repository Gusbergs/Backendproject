package com.example.backendproject.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContractCustomer {

    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    //@Column(name="id")


    @JacksonXmlProperty(localName = "id")
    public Long contractCustomerId;


    public String companyName;
    public String contactName;
    public String contactTitle;
    public String streetAddress;
    public String city;
    public int postalCode;
    public String country;
    public String phone;
    public String fax;


}
