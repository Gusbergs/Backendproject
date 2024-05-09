package com.example.backendproject.repo;

import com.example.backendproject.models.ContractCustomer;
import com.example.backendproject.models.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipperRepo extends JpaRepository<Shipper, Long> {
}
