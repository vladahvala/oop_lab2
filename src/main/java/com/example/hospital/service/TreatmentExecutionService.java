package com.example.hospital.service;

import com.example.hospital.dto.TreatmentExecutionRequest;
import com.example.hospital.entity.*;
import com.example.hospital.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PreAuthorize("hasAnyRole('DOCTOR','NURSE')")
    public TreatmentExecution create(TreatmentExecutionRequest req) {

        Treatment treatment = treatmentRepository.findById(req.treatmentId)
                .orElseThrow(() -> new RuntimeException("Treatment not found"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isNurse = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_NURSE"));

        boolean isDoctor = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"));

        // 🚫 RULE: nurse cannot do surgery
        if (isNurse && treatment.getType() == TreatmentType.SURGERY) {
            throw new RuntimeException("Nurse cannot perform surgery");
        }

        TreatmentExecution execution = new TreatmentExecution();
        execution.setTreatment(treatment);
        execution.setExecutor(user);

        if (isNurse) {
            execution.setNurse(user);
        }

        if (isDoctor) {
            execution.setDoctor(user);
        }

        return repository.save(execution);
    }

    public List<TreatmentExecution> getAll() {
        return repository.findAll();
    }
}