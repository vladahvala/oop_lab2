package com.example.hospital.service;

import com.example.hospital.entity.Diagnosis;
import com.example.hospital.repository.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    private final DiagnosisRepository repository;

    public DiagnosisService(DiagnosisRepository repository) {
        this.repository = repository;
    }

    public Diagnosis create(Diagnosis diagnosis) {
        return repository.save(diagnosis);
    }

    public List<Diagnosis> getAll() {
        return repository.findAll();
    }
}