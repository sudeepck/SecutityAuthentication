package com.org.SpringSecurity.dto;

import com.org.SpringSecurity.Entity.Type.BloodGroupType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class PatientRequestDto {
    private String name;
    private LocalDate birthDate;
    private String email;
    private String gender;
    private BloodGroupType bloodGroup;
}
