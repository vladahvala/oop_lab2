package com.example.hospital.controller;

import com.example.hospital.entity.TreatmentExecution;
import com.example.hospital.service.TreatmentExecutionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/executions")
public class TreatmentExecutionController {

    private final TreatmentExecutionService service;

    public TreatmentExecutionController(TreatmentExecutionService service) {
        this.service = service;
    }

    @PostMapping
    public TreatmentExecution create(@RequestBody TreatmentExecution execution) {
        return service.create(execution);
    }

    @GetMapping
    public List<TreatmentExecution> getAll() {
        return service.getAll();
    }
}