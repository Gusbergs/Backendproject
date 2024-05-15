package com.example.backendproject.repo;

import com.example.backendproject.models.ContractCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractCustomerRepo extends JpaRepository<ContractCustomer, Long> {
}
