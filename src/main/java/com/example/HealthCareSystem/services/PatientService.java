package com.example.HealthCareSystem.services;

import com.example.HealthCareSystem.entities.SuggestDoctorResponse;
import com.example.HealthCareSystem.entities.Doctor;
import com.example.HealthCareSystem.entities.Patient;
import com.example.HealthCareSystem.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final DoctorService doctorService;
    static Map<String,String> symptomCategoryMap;
    static
    {
        symptomCategoryMap =new HashMap<String,String>();
        symptomCategoryMap.put("Arthritis","Orthopedic");
        symptomCategoryMap.put("BackPain","Orthopedic");
        symptomCategoryMap.put("Tissueinjuries","Orthopedic");
        symptomCategoryMap.put("Dysmenorrhea","Gynecology");
        symptomCategoryMap.put("Skininfection","Dermatology");
        symptomCategoryMap.put("skinburn","Dermatology");
        symptomCategoryMap.put("Earpain","ENT");
    }
    @Autowired
    public PatientService(PatientRepository patientRepository, DoctorService doctorService) {
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
    }
    public Patient createPatient(Patient patient ) {
        return patientRepository.save(patient);
    }
    public String deletePatient(Long id) {
        if(null != patientRepository.findById(String.valueOf(id)).orElse(null)) {
            patientRepository.deleteById(String.valueOf(id));
        }
        else
        {
            return "Patient with ID "+id+" not present in database";
        }
        return "Patient Deleted";
    }
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        return patientRepository.findById(String.valueOf(id)).orElse(null);
    }
    public SuggestDoctorResponse suggestDoctor(Long patientId) {
        Patient patient=findById(patientId);
        String patientSymptomCategory= symptomCategoryMap.get(String.valueOf(patient.getSymptom()));
        List<Doctor> suggestedDoctors=doctorService.findByCityAndSpeciality(patient.getCity(), patientSymptomCategory);
        SuggestDoctorResponse suggestDoctorResponse=new SuggestDoctorResponse();
        suggestDoctorResponse.setDoctors(suggestedDoctors);
        if(CollectionUtils.isEmpty(suggestedDoctors))
        {
            boolean isDoctorPresentInSameCity=doctorService.isDoctorPresentInSameCity(patient.getCity());
            boolean isDoctorPresentForSameSpeciality=doctorService.isDoctorPresentForSameSpeciality(patientSymptomCategory);
            if(!isDoctorPresentInSameCity)
            {
                suggestDoctorResponse.setErrorMessage("We are still waiting to expand to your location");
            }
            else if (!isDoctorPresentForSameSpeciality)
            {
                suggestDoctorResponse.setErrorMessage("There isn’t any doctor present at your location for your symptom");
            }
        }
        return suggestDoctorResponse;
    }
}
