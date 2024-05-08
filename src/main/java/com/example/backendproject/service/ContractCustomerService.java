package com.example.backendproject.service;

import com.example.backendproject.dto.BookingDtoMini;
import com.example.backendproject.dto.ContractCustomersDtoDetailed;
import com.example.backendproject.dto.ContractCustomersDtoMini;
import com.example.backendproject.models.ContractCustomer;
import com.example.backendproject.repo.ContractCustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractCustomerService {

    final ContractCustomerRepo contractCustomerRepo;


    public ContractCustomersDtoMini contractCustomersDtoMini(ContractCustomer contractCustomer){
        return ContractCustomersDtoMini.builder().companyName(contractCustomer.companyName).contactName(contractCustomer.contactName)
                .country(contractCustomer.getCountry()).build();
    }


    public List<ContractCustomersDtoMini> getAllContractCustomersDtoMini(){
        return contractCustomerRepo.findAll().stream().map(customer -> contractCustomersDtoMini(customer)).toList();
    }

    public void saveContractCustomer(ContractCustomer customer) {
        contractCustomerRepo.save(customer);
    }

}
