package com.example.hospital.controller;

import com.example.hospital.dto.TreatmentDTO;
import com.example.hospital.dto.TreatmentRequest;
import com.example.hospital.entity.Treatment;
import com.example.hospital.service.TreatmentService;

import org.springframework.security.core.Authentication;
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
    public Treatment create(@RequestBody TreatmentRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<TreatmentDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/debug")
    public String debug(Authentication auth) {
        return auth.getAuthorities().toString();
    }

    @GetMapping("/my")
    public List<TreatmentDTO> my(Authentication auth) {
        return service.getByPatient(auth.getName());
    }
}