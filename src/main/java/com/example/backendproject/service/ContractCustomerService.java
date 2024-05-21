package com.example.backendproject.service;

import com.example.backendproject.dto.ContractCustomersDtoMini;
import com.example.backendproject.models.ContractCustomer;
import com.example.backendproject.repo.ContractCustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractCustomerService {

    final ContractCustomerRepo contractCustomerRepo;




    public ContractCustomersDtoMini contractCustomersDtoMini(ContractCustomer contractCustomer){
        return ContractCustomersDtoMini.builder().id(contractCustomer.contractCustomerId).companyName(contractCustomer.companyName).contactName(contractCustomer.contactName)
                .country(contractCustomer.getCountry()).build();
    }


    public List<ContractCustomersDtoMini> getAllContractCustomersDtoMini(){
        return contractCustomerRepo.findAll().stream().map(customer -> contractCustomersDtoMini(customer)).toList();
    }

    public void saveContractCustomer(ContractCustomer customer) {
        contractCustomerRepo.save(customer);
    }

    public Optional<ContractCustomer> getContractCustomerById(Long id) {
        System.out.println("ID: " + id);

        return contractCustomerRepo.findById(id);
    }
}
