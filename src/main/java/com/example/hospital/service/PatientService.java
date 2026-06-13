package com.example.hospital.service;

import com.example.hospital.dto.DischargeRequest;
import com.example.hospital.dto.PatientRequest;
import com.example.hospital.entity.Patient;
import com.example.hospital.entity.PatientStatus;
import com.example.hospital.entity.User;
import com.example.hospital.entity.Role;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.security.SecurityUtils;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @PreAuthorize("hasRole('DOCTOR')")
    public Patient create(PatientRequest req) {

        String username = SecurityUtils.getCurrentUsername();

        User creator = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Patient patient = new Patient();
        patient.setFullName(req.fullName);
        patient.setBirthDate(req.birthDate);
        patient.setStatus(PatientStatus.ADMITTED);

        return repository.save(patient);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public Patient discharge(Long patientId, DischargeRequest req) {

        Patient patient = repository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setStatus(PatientStatus.DISCHARGED);

        return repository.save(patient);
    }

    public List<Patient> getAll() {
        return repository.findAll();
    }

}