package com.example.hospital.service;

import com.example.hospital.entity.Diagnosis;
import com.example.hospital.entity.User;
import com.example.hospital.repository.DiagnosisRepository;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    private final DiagnosisRepository repository;
    private final UserRepository userRepository;

    public DiagnosisService(DiagnosisRepository repository,
            UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Diagnosis create(Diagnosis diagnosis) {

        String username = SecurityUtils.getCurrentUsername();

        User doctor = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        diagnosis.setDoctor(doctor);

        return repository.save(diagnosis);
    }

    public List<Diagnosis> getAll() {
        return repository.findAll();
    }
}