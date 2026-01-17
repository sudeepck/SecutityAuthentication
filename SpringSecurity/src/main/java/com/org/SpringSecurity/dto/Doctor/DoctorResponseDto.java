package com.org.SpringSecurity.dto.Doctor;


import com.org.SpringSecurity.dto.InsuranceResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDto {
    private Long id;
    private  String name;
    private String specilazation;
    private  String email;
    private InsuranceResponseDto insurance;
}
