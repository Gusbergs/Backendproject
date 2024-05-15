package com.example.backendproject.repo;

import com.example.backendproject.models.Queue;
import org.springframework.data.repository.CrudRepository;

public interface QueueRepo extends CrudRepository<Queue, Long> {
}
