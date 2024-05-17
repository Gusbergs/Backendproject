package com.example.backendproject.repo;

import com.example.backendproject.models.Customer;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.Annotation;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    Customer getReferenceByEmail(String email);

    void deleteByEmail(String email);
}
