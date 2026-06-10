package com.example.hospital.service;

import com.example.hospital.entity.Patient;
import com.example.hospital.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient create(Patient patient) {
        return repository.save(patient);
    }

    public List<Patient> getAll() {
        return repository.findAll();
    }
}