package com.example.hospital.repository;

import com.example.hospital.entity.Treatment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByDiagnosisId(Long diagnosisId);
}