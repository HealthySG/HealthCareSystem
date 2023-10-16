package com.example.HealthCareSystem.entities;

import java.util.List;

public class SuggestDoctorResponse {

        String errorMessage;
        List<Doctor> doctors;

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public List<Doctor> getDoctors() {
            return doctors;
        }

        public void setDoctors(List<Doctor> doctors) {
            this.doctors = doctors;
        }

}
