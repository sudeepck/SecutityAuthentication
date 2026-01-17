package com.org.SpringSecurity;

import com.org.SpringSecurity.Entity.Appointment;
import com.org.SpringSecurity.Entity.Doctor;
import com.org.SpringSecurity.Entity.Insurance;
import com.org.SpringSecurity.Entity.Patient;
import com.org.SpringSecurity.Repository.PatientsRepository;
import com.org.SpringSecurity.Service.AppointmentService;
import com.org.SpringSecurity.Service.InsuranceService;
import com.org.SpringSecurity.Service.PatientService;
import com.org.SpringSecurity.dto.Appointment.AppointmentResponseDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;

    @Test
    public void testAppattment() {
        Appointment appointment  = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,1,22,22,20))
                .reason("cancer")
                .build();

        AppointmentResponseDto appointmentData = appointmentService.createNewAppointment(appointment, 1L,1L);


        AppointmentResponseDto UpdatedAppointment = appointmentService.reassignAppointmentToAnotherDoctor(appointmentData.getId(),3L);
        System.out.println("UpdatedAppointment : " + " " + UpdatedAppointment);
    }

    @Test void createAppointmentAndDeletePatient(){
        Appointment appointment1  = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,1,22,22,20))
                .reason("cancer")
                .build();
        Appointment appointment2  = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,1,22,22,20))
                .reason("cancer")
                .build();
        Appointment appointment3  = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,1,22,22,20))
                .reason("cancer")
                .build();
        AppointmentResponseDto appointmentData1 = appointmentService.createNewAppointment(appointment1, 1L,1L);
        AppointmentResponseDto appointmentData2 = appointmentService.createNewAppointment(appointment2, 1L,1L);
        AppointmentResponseDto appointmentData3= appointmentService.createNewAppointment(appointment3, 1L,1L);


        System.out.println(appointmentData1);
        System.out.println(appointmentData2);
        System.out.println(appointmentData3);
        String val = patientService.DeletepatientById(1L);
        System.out.println(val);

        System.out.println(appointmentService.getAllAppointment());
    }
}
