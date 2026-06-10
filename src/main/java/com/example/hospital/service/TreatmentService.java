package com.example.hospital.service;

import com.example.hospital.entity.Treatment;
import com.example.hospital.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository repository;

    public TreatmentService(TreatmentRepository repository) {
        this.repository = repository;
    }

    public Treatment create(Treatment treatment) {
        return repository.save(treatment);
    }

    public List<Treatment> getAll() {
        return repository.findAll();
    }
}