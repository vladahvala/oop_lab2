package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "treatments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    @JsonIgnore
    private Diagnosis diagnosis;

    @Enumerated(EnumType.STRING)
    private TreatmentType type;
    // medicine / procedure / surgery

    private String description;

    @Enumerated(EnumType.STRING)
    private AssignedByRole assignedByRole;

    @Enumerated(EnumType.STRING)
    private TreatmentStatus status = TreatmentStatus.PENDING;
}