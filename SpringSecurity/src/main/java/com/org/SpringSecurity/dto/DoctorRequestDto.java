package com.org.SpringSecurity.dto;

import com.org.SpringSecurity.Entity.Appointment;
import com.org.SpringSecurity.Entity.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequestDto {

    private  String name;
    private String specilazation;
    private  String email;
}
