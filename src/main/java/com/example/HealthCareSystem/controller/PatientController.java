package com.example.HealthCareSystem.controller;

import com.example.HealthCareSystem.entities.Doctor;
import com.example.HealthCareSystem.entities.Patient;
import com.example.HealthCareSystem.entities.SuggestDoctorResponse;
import com.example.HealthCareSystem.services.DoctorService;
import com.example.HealthCareSystem.services.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
    Logger log = LoggerFactory.getLogger(DoctorController.class);
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService=doctorService;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addPatient(@Valid @RequestBody Patient patient) {
        Patient newPatient= patientService.createPatient(patient);
        if(null != newPatient)
         return "Patient Added with ID "+newPatient.getId();
        return "An Error Occured during adding new Patient";
    }
    @DeleteMapping("/remove/{id}")
    @ResponseBody
    public String removePatient(@PathVariable Long id) {
           return patientService.deletePatient(id);
    }
    @GetMapping("/all")
    public List<Patient> getAllDoctors() {
        return patientService.getAllPatients();
    }

    @PostMapping("/suggestDoctor/{patientId}")
    public SuggestDoctorResponse suggestDoctor(@PathVariable Long patientId)
    {
        return patientService.suggestDoctor(patientId);
    }

}


