package com.example.hospital.service;

import com.example.hospital.entity.Patient;
import com.example.hospital.entity.User;
import com.example.hospital.entity.Role;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repository;
    private final UserRepository userRepository;

    public PatientService(PatientRepository repository,
            UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Patient create(Patient patient) {

        String username = SecurityUtils.getCurrentUsername();

        User creator = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (creator.getRole() != Role.DOCTOR) {
            throw new RuntimeException("Only doctor can create patient");
        }

        return repository.save(patient);
    }

    public List<Patient> getAll() {
        return repository.findAll();
    }
}