package com.org.SpringSecurity.dto;


import com.org.SpringSecurity.Entity.Type.BloodGroupType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class PatientResponseDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private String gender;
    private LocalDateTime createdAt;
    private BloodGroupType bloodGroup;
    private InsuranceResponseDto insurance;
}

