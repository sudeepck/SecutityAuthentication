package com.org.SpringSecurity;

import com.org.SpringSecurity.Entity.Doctor;
import com.org.SpringSecurity.Entity.Insurance;
import com.org.SpringSecurity.Entity.Patient;
import com.org.SpringSecurity.Service.DoctorService;
import com.org.SpringSecurity.Service.InsuranceService;
import com.org.SpringSecurity.dto.DoctorRequestDto;
import com.org.SpringSecurity.dto.DoctorResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;

    @Test
    public void TestAddDoctor() {
        DoctorRequestDto doctor  = DoctorRequestDto.builder()
                .name("Dr. Rakesh Metha")
                .specilazation("cardiology")
                .email("rakesh.metha@gmail.com")
                .build();
        DoctorResponseDto doctorData = doctorService.AddDoctor(doctor);
        System.out.println("doctor: " + " " + doctorData);
    }
}
