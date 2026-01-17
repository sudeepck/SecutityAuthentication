package com.org.SpringSecurity;

import com.org.SpringSecurity.Entity.Insurance;
import com.org.SpringSecurity.Entity.Patient;
import com.org.SpringSecurity.Service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testInsurance() {
        Insurance insurance  = Insurance.builder()
                .polciyNumber("HDFC_tdd")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println("patient: " + " " + patient);
//        Patient patient1 = insuranceService.disaccociateInsuranceFromPatient(patient.getId());
//        System.out.println("patient1: " + " " + patient1);
    }
}
