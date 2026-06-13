package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "treatment_executions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "nurse_id", nullable = true)
    @JsonIgnore
    private User nurse;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    @JsonIgnore
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "executor_id", nullable = false)
    @JsonIgnore
    private User executor;

    private LocalDateTime executedAt = LocalDateTime.now();
}