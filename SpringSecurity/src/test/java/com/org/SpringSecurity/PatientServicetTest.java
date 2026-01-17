package com.org.SpringSecurity;

import com.org.SpringSecurity.Entity.Insurance;
import com.org.SpringSecurity.Entity.Patient;
import com.org.SpringSecurity.Service.InsuranceService;
import com.org.SpringSecurity.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientServicetTest {

    @Autowired
    private PatientService patientService;

    @Test
    public void TestPatientRepository() {
        List<Patient> patients = patientService.fetchAllPatient();
        System.out.println(patients);
    }
}
