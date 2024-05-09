package com.example.backendproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipperDtoDetailed {

    public long id;


    public String email;
    public String companyName;
    public String contactName;
    public String contactTitle;
    public String streetAddress;
    public String city;
    public String postalCode;
    public String country;
    public String phone;
    public String fax;
}
