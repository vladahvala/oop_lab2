package com.example.hospital.service;

import com.example.hospital.dto.TreatmentRequest;
import com.example.hospital.entity.*;
import com.example.hospital.repository.*;
import com.example.hospital.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository repository;
    private final UserRepository userRepository;
    private final DiagnosisRepository diagnosisRepository;

    public TreatmentService(TreatmentRepository repository,
            UserRepository userRepository,
            DiagnosisRepository diagnosisRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.diagnosisRepository = diagnosisRepository;
    }

    public Treatment create(TreatmentRequest req) {

        String username = SecurityUtils.getCurrentUsername();

        User doctor = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (doctor.getRole() != Role.DOCTOR) {
            throw new RuntimeException("Only doctor can create treatment");
        }

        Diagnosis diagnosis = diagnosisRepository.findById(req.diagnosisId)
                .orElseThrow(() -> new RuntimeException("Diagnosis not found"));

        Treatment treatment = new Treatment();
        treatment.setDiagnosis(diagnosis);
        treatment.setType(req.type);
        treatment.setDescription(req.description);
        treatment.setAssignedByRole(AssignedByRole.DOCTOR);

        return repository.save(treatment);
    }

    public List<Treatment> getAll() {
        return repository.findAll();
    }
}