package com.example.hospital.service;

import com.example.hospital.dto.TreatmentRequest;
import com.example.hospital.entity.*;
import com.example.hospital.repository.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository repository;
    private final DiagnosisRepository diagnosisRepository;

    public TreatmentService(TreatmentRepository repository,
            DiagnosisRepository diagnosisRepository) {
        this.repository = repository;
        this.diagnosisRepository = diagnosisRepository;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public Treatment create(TreatmentRequest req) {

        Diagnosis diagnosis = diagnosisRepository.findById(req.diagnosisId)
                .orElseThrow(() -> new RuntimeException("Diagnosis not found"));

        Treatment treatment = new Treatment();
        treatment.setDiagnosis(diagnosis);
        treatment.setType(req.type); // ✔ enum
        treatment.setDescription(req.description);
        treatment.setAssignedByRole(AssignedByRole.DOCTOR);

        return repository.save(treatment);
    }

    public List<Treatment> getAll() {
        return repository.findAll();
    }
}