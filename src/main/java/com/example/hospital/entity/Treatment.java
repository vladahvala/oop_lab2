package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Diagnosis diagnosis;

    private String type;
    // medicine / procedure / surgery

    private String description;

    @Enumerated(EnumType.STRING)
    private AssignedByRole assignedByRole;

    @Enumerated(EnumType.STRING)
    private TreatmentStatus status = TreatmentStatus.PENDING;
}