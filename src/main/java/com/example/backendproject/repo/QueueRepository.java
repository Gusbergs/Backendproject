package com.example.backendproject.repo;

import com.example.backendproject.models.QueueModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueRepository extends JpaRepository<QueueModel, Long> {



}
