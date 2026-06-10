package com.example.hospital.controller;

import com.example.hospital.entity.Treatment;
import com.example.hospital.service.TreatmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatments")
public class TreatmentController {

    private final TreatmentService service;

    public TreatmentController(TreatmentService service) {
        this.service = service;
    }

    @PostMapping
    public Treatment create(@RequestBody Treatment treatment) {
        return service.create(treatment);
    }

    @GetMapping
    public List<Treatment> getAll() {
        return service.getAll();
    }
}