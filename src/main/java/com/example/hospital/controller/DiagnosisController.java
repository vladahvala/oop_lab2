package com.example.hospital.controller;

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
    public Diagnosis create(@RequestBody Diagnosis diagnosis) {
        return service.create(diagnosis);
    }

    @GetMapping
    public List<Diagnosis> getAll() {
        return service.getAll();
    }
}