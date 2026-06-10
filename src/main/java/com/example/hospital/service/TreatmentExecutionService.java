package com.example.hospital.service;

import com.example.hospital.dto.TreatmentExecutionRequest;
import com.example.hospital.entity.Treatment;
import com.example.hospital.entity.TreatmentExecution;
import com.example.hospital.entity.User;
import com.example.hospital.repository.TreatmentExecutionRepository;
import com.example.hospital.repository.TreatmentRepository;
import com.example.hospital.repository.UserRepository;

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

        TreatmentExecution execution = new TreatmentExecution();

        Treatment treatment = treatmentRepository.findById(req.treatmentId)
                .orElseThrow(() -> new RuntimeException("Treatment not found"));
        execution.setTreatment(treatment);

        User executor = userRepository.findById(req.executorId)
                .orElseThrow(() -> new RuntimeException("Executor not found"));
        execution.setExecutor(executor);

        if (req.doctorId != null) {
            User doctor = userRepository.findById(req.doctorId)
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            execution.setDoctor(doctor);
        }

        if (req.nurseId != null) {
            User nurse = userRepository.findById(req.nurseId)
                    .orElseThrow(() -> new RuntimeException("Nurse not found"));
            execution.setNurse(nurse);
        }

        return repository.save(execution);
    }

    public List<TreatmentExecution> getAll() {
        return repository.findAll();
    }
}