package com.example.HealthCareSystem.repositories;

import com.example.HealthCareSystem.entities.Doctor;
import com.example.HealthCareSystem.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, String> {
    @Query("SELECT DISTINCT D.name AS Doctor_Name FROM Patient P INNER JOIN SymptomCategory S ON P.symptom = S.name INNER JOIN Doctor D ON S.category = D.speciality and P.city=D.email and P.id=:id")
    List<Doctor> suggestDoctor(@Param("id") Long id);
}
