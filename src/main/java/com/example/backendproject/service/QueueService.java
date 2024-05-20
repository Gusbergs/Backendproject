package com.example.backendproject.service;

import com.example.backendproject.models.Queue;
import com.example.backendproject.repo.QueueRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueService {

    QueueRepo queueRepo;

    public List<Queue> getAllQueue() {
        return (List<Queue>) queueRepo.findAll();
    }
}
