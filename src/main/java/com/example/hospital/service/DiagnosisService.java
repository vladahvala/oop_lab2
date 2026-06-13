package com.example.hospital.service;

import com.example.hospital.dto.DiagnosisRequest;
import com.example.hospital.entity.*;
import com.example.hospital.repository.*;
import com.example.hospital.security.SecurityUtils;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    private final DiagnosisRepository repository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public DiagnosisService(DiagnosisRepository repository,
            UserRepository userRepository,
            PatientRepository patientRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public Diagnosis create(DiagnosisRequest req) {

        String username = SecurityUtils.getCurrentUsername();

        User doctor = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (doctor.getRole() != Role.DOCTOR) {
            throw new RuntimeException("Only doctor can create diagnosis");
        }

        Patient patient = patientRepository.findById(req.patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDoctor(doctor);
        diagnosis.setPatient(patient);
        diagnosis.setDescription(req.description);

        return repository.save(diagnosis);
    }

    public Diagnosis markAsFinal(Long diagnosisId) {

        Diagnosis diagnosis = repository.findById(diagnosisId)
                .orElseThrow(() -> new RuntimeException("Diagnosis not found"));

        diagnosis.setFinalDiagnosis(true);

        return repository.save(diagnosis);
    }

    public List<Diagnosis> getAll() {
        return repository.findAll();
    }

    public List<Diagnosis> getByPatient(String username) {
        return repository.findByPatient_User_Username(username);
    }
}