package com.example.hospital.repository;

import com.example.hospital.entity.Diagnosis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    List<Diagnosis> findByPatient_User_Username(String username);
}