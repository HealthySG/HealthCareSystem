package com.example.HealthCareSystem.controller;

import com.example.HealthCareSystem.entities.Doctor;
import com.example.HealthCareSystem.services.DoctorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    Logger log = LoggerFactory.getLogger(DoctorController.class);
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addDoctor(@RequestBody @Valid Doctor doctor) {
        Doctor newDoc= doctorService.createDoctor(doctor);
        if(null != newDoc)
            return "Doctor Added with ID "+newDoc.getId();
        return "An Error Occured during adding new Doctor";
    }
    @DeleteMapping("/remove/{id}")
    @ResponseBody
    public String removeDoctor(@PathVariable Long id) {
          return doctorService.deleteDoctor(id);
    }
    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
