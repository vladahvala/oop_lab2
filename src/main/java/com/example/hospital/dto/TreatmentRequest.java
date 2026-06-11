package com.example.hospital.dto;

import com.example.hospital.entity.TreatmentType;

public class TreatmentRequest {
    public Long diagnosisId;
    public TreatmentType type;
    public String description;
}