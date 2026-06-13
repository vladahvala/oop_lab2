package com.example.hospital.controller;

import com.example.hospital.dto.DiagnosisRequest;
import com.example.hospital.entity.Diagnosis;
import com.example.hospital.security.SecurityUtils;
import com.example.hospital.service.DiagnosisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnoses")
public class DiagnosisController {

    private final DiagnosisService service;

    public DiagnosisController(DiagnosisService service) {
        this.service = service;
    }

    @PostMapping
    public Diagnosis create(@RequestBody DiagnosisRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}/final")
    public Diagnosis makeFinal(@PathVariable Long id) {
        return service.markAsFinal(id);
    }

    @GetMapping
    public List<Diagnosis> getAll() {
        return service.getAll();
    }

    @GetMapping("/my")
    public List<Diagnosis> getMy() {
        String username = SecurityUtils.getCurrentUsername();
        return service.getByPatient(username);
    }
}