package com.org.SpringSecurity;

import com.org.SpringSecurity.Service.DoctorService;
import com.org.SpringSecurity.dto.Doctor.DoctorRequestDto;
import com.org.SpringSecurity.dto.Doctor.DoctorResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
