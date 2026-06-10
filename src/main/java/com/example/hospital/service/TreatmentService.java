package com.example.hospital.service;

import com.example.hospital.entity.*;
import com.example.hospital.repository.*;
import com.example.hospital.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository repository;
    private final UserRepository userRepository;

    public TreatmentService(TreatmentRepository repository,
            UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Treatment create(Treatment treatment) {

        String username = SecurityUtils.getCurrentUsername();

        User doctor = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (doctor.getRole() != Role.DOCTOR) {
            throw new RuntimeException("Only doctor can create treatment");
        }

        treatment.setAssignedByRole(AssignedByRole.DOCTOR);

        return repository.save(treatment);
    }

    public List<Treatment> getAll() {
        return repository.findAll();
    }
}