package com.example.backendproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractCustomersDtoMini {

    public String companyName;
    public String contactName;
    public String country;

}
