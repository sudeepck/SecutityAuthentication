package com.org.SpringSecurity.Service;

import com.org.SpringSecurity.Entity.Insurance;
import com.org.SpringSecurity.Entity.Patient;
import com.org.SpringSecurity.Repository.InsuranceRepository;
import com.org.SpringSecurity.Repository.PatientsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientsRepository patientsRepository;


    @Transactional
    public  Patient assignInsuranceToPatient(Insurance insurance, Long patient_id){
        Patient patient = patientsRepository.findById(patient_id)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not found with id:" + " " + patient_id));

        if(patient.getInsurance() != null){
            throw  new EntityNotFoundException("Insurance already Exists:" + " "+ patient.getInsurance());
        }
            patient.setInsurance(insurance); // cascading
            insurance.setPatient(patient);

        return  patient;
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId){
        Patient patient = patientsRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not found with id:" + " " + patientId));

        // removed the insurance and also deleted the insurance by adding (orphanRemoval = true in patient)
        patient.setInsurance(null);
        return  patient;
    }

}
