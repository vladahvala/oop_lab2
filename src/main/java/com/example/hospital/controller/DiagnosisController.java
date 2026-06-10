package com.example.hospital.controller;

import com.example.hospital.dto.DiagnosisRequest;
import com.example.hospital.entity.Diagnosis;
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

    @GetMapping
    public List<Diagnosis> getAll() {
        return service.getAll();
    }
}