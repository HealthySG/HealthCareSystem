package com.example.HealthCareSystem.repositories;

import com.example.HealthCareSystem.entities.City;
import com.example.HealthCareSystem.entities.Doctor;
import com.example.HealthCareSystem.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    List<Doctor> findByCityAndSpeciality(City city, Speciality speciality);

    boolean existsByCity(City city);

    boolean existsBySpeciality(Speciality speciality);
}
