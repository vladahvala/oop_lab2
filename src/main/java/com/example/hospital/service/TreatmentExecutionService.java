package com.example.hospital.service;

import com.example.hospital.dto.TreatmentExecutionRequest;
import com.example.hospital.entity.*;
import com.example.hospital.repository.*;
import com.example.hospital.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentExecutionService {

    private final TreatmentExecutionRepository repository;
    private final TreatmentRepository treatmentRepository;
    private final UserRepository userRepository;

    public TreatmentExecutionService(
            TreatmentExecutionRepository repository,
            TreatmentRepository treatmentRepository,
            UserRepository userRepository) {
        this.repository = repository;
        this.treatmentRepository = treatmentRepository;
        this.userRepository = userRepository;
    }

    public TreatmentExecution create(TreatmentExecutionRequest req) {

        String username = SecurityUtils.getCurrentUsername();

        User executor = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Treatment treatment = treatmentRepository.findById(req.treatmentId)
                .orElseThrow(() -> new RuntimeException("Treatment not found"));

        if (executor.getRole() == Role.NURSE &&
                treatment.getType().equalsIgnoreCase("SURGERY")) {
            throw new RuntimeException("Nurse cannot perform surgery");
        }

        if (executor.getRole() == Role.PATIENT) {
            throw new RuntimeException("Patient cannot execute treatments");
        }

        TreatmentExecution execution = new TreatmentExecution();
        execution.setTreatment(treatment);
        execution.setExecutor(executor);

        return repository.save(execution);
    }

    public List<TreatmentExecution> getAll() {
        return repository.findAll();
    }
}