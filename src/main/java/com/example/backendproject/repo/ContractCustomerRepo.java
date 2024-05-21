package com.example.backendproject.repo;

import com.example.backendproject.dto.ContractCustomersDtoMini;
import com.example.backendproject.models.ContractCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContractCustomerRepo extends CrudRepository<ContractCustomer, Long> {
    Page<ContractCustomer> findAll(Pageable pageable);
    Page<ContractCustomer> findAllByCountryContainsOrContactNameContainsOrCompanyNameContains(String country,String contactName,String companyName, Pageable pageable);

}
