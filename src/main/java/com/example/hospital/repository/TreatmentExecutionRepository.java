package com.example.hospital.repository;

import com.example.hospital.entity.TreatmentExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentExecutionRepository extends JpaRepository<TreatmentExecution, Long> {
}