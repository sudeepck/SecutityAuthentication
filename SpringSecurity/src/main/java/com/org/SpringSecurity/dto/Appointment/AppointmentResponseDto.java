package com.org.SpringSecurity.dto.Appointment;

import com.org.SpringSecurity.dto.Doctor.DoctorAppointmentDto;
import com.org.SpringSecurity.dto.Doctor.DoctorResponseDto;
import com.org.SpringSecurity.dto.Patient.PatientResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private DoctorAppointmentDto doctor;
    private PatientResponseDTO patient;
}
