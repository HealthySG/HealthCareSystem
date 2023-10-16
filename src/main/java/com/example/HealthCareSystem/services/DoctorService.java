package com.example.HealthCareSystem.services;

import com.example.HealthCareSystem.entities.City;
import com.example.HealthCareSystem.entities.Doctor;
import com.example.HealthCareSystem.entities.Speciality;
import com.example.HealthCareSystem.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    public String deleteDoctor(Long id) {
        if(null != doctorRepository.findById(String.valueOf(id)).orElse(null)) {
            doctorRepository.deleteById(String.valueOf(id));
        }
        else
        {
            return "Doctor with ID "+id+" not present in database";
        }
        return "Doctor Deleted";
    }
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    public List<Doctor> findByCityAndSpeciality(String city, String speciality) {
        return doctorRepository.findByCityAndSpeciality(City.valueOf(city), Speciality.valueOf(speciality));
    }
    public boolean isDoctorPresentInSameCity(String city) {
        return doctorRepository.existsByCity(City.valueOf(city));
    }
    public boolean isDoctorPresentForSameSpeciality(String speciality ){
        return doctorRepository.existsBySpeciality(Speciality.valueOf(speciality));
    }
}
