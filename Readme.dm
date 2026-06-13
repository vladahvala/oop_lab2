This is a backend REST API system for managing hospital processes. The system allows doctors to diagnose patients, assign treatments, and manage patient discharge with a final diagnosis record. Nurses can execute specific treatments based on role-based permissions.
The project is built using Spring Boot and related technologies, focusing on clean architecture, role-based security, and real-world hospital workflow simulation.

Technologies Used
Java 17+
Spring Boot
Spring Security (JWT-based authentication)
Spring Data JPA (Hibernate)
PostgreSQL
Liquibase (database migrations)
Maven


Authentication & Authorization
The system uses JWT-based authentication.


Roles:
DOCTOR – full access to diagnoses, treatments, and patient management
NURSE – can execute allowed treatments (e.g., procedures, medications)
PATIENT – limited access (view-related operations)

All endpoints (except authentication) require a valid JWT token.


Core Features
Patient Management:
Create patients
View patient list
Discharge patient from hospital
Store:
discharge status
discharge timestamp
final diagnosis text

Diagnosis Module:
Doctors create diagnoses for patients
Mark diagnosis as final
Retrieve diagnoses by patient

Treatment Management:
Doctors assign treatments (medication, procedure, surgery)
Treatments are linked to diagnoses
Role-based restriction for treatment types

Teatment Execution:
Nurses and doctors can execute treatments
Nurses cannot perform surgical treatments
Execution is logged with executor information


Database
Database schema is managed using Liquibase.


Main entities:
users
patients
diagnoses
treatments
treatment_executions


Business Workflow:
Doctor creates a diagnosis
Doctor assigns treatment based on diagnosis
Nurse or doctor executes treatment
Patient is discharged with final diagnosis