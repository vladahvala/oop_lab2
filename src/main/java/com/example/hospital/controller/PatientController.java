package com.example.hospital.controller;

import com.example.hospital.dto.PatientRequest;
import com.example.hospital.entity.Patient;
import com.example.hospital.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public Patient create(@RequestBody PatientRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<Patient> getAll() {
        return service.getAll();
    }
}