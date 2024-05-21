package com.example.backendproject.service;

import com.example.backendproject.dto.ContractCustomersDtoMini;
import com.example.backendproject.dto.ShipperDtoMini;
import com.example.backendproject.models.ContractCustomer;
import com.example.backendproject.models.Shipper;
import com.example.backendproject.repo.ShipperRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipperService {

    final ShipperRepo shipperRepo;

    public ShipperDtoMini shipperDtoMini(Shipper shipper) {
        return ShipperDtoMini.builder().id(shipper.getId())
                .companyName(shipper.getCompanyName())
                .phone(shipper.getPhone()).build();
    }

    public List<ShipperDtoMini> getAllShippersDtoMini(){
        return shipperRepo.findAll().stream().map(this::shipperDtoMini).toList();
    }
    public void saveShipper(Shipper shipper) {
        shipperRepo.save(shipper);
    }

}
