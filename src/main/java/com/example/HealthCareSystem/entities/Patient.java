package com.example.HealthCareSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20, message = "Invalid city : Maximum Length accepted is 20")
    private String city;
    @Enumerated(EnumType.STRING)
    private Symptom symptom;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }
}
