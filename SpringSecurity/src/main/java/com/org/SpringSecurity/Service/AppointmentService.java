package com.org.SpringSecurity.Service;

import com.org.SpringSecurity.Entity.Appointment;
import com.org.SpringSecurity.Entity.Doctor;
import com.org.SpringSecurity.Entity.Patient;
import com.org.SpringSecurity.Repository.AppointmentRepository;
import com.org.SpringSecurity.Repository.DoctorRepository;
import com.org.SpringSecurity.Repository.PatientsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private  final AppointmentRepository appointmentRepository;
    private  final DoctorRepository doctorRepository;
    private  final PatientsRepository patientsRepository;



    @Transactional
    public  Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("No doctore found with id:" + " " + doctorId));
        Patient patient = patientsRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not found with id:" + " " + patientId));

        if(appointment.getId() != null)
            throw  new IllegalArgumentException("Appointment should not have ");


        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);// to maintain bidirectionly
        return appointmentRepository.save(appointment); // save required becz creating appointment for the first Time
    }

    @Transactional
    public  Appointment reassignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("No doctore found with id:" + " " + doctorId));
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("No appointment found with id:" + " " + appointmentId));

        appointment.setDoctor(doctor);
        doctor.getAppointment().add(appointment); // to maintain biDirectionl
        return appointment;
    }

    @Transactional
    public List<Appointment> getAllAppointment(){
        return  appointmentRepository.findAll();
    }

    public Appointment AddAppointment(Appointment appointment){
        return  appointmentRepository.save(appointment);
    }
}
