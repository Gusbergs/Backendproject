package com.example.backendproject.repo;

import com.example.backendproject.models.ContractCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContractCustomerRepo extends JpaRepository<ContractCustomer, Long> {
}
