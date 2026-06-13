package com.example.hospital.repository;

import com.example.hospital.entity.Diagnosis;
import com.example.hospital.entity.Treatment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    List<Treatment> findByDiagnosisId(Long diagnosisId);

    List<Treatment> findByDiagnosis_Patient_User_Username(String username);
}