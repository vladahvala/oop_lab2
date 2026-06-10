package com.example.hospital.service;

import com.example.hospital.entity.TreatmentExecution;
import com.example.hospital.repository.TreatmentExecutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentExecutionService {

    private final TreatmentExecutionRepository repository;

    public TreatmentExecutionService(TreatmentExecutionRepository repository) {
        this.repository = repository;
    }

    public TreatmentExecution create(TreatmentExecution execution) {
        return repository.save(execution);
    }

    public List<TreatmentExecution> getAll() {
        return repository.findAll();
    }
}