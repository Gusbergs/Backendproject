package com.example.backendproject.repo;

import com.example.backendproject.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
