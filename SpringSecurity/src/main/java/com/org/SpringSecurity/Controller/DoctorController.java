package com.org.SpringSecurity.Controller;

import com.org.SpringSecurity.Service.DoctorService;
import com.org.SpringSecurity.dto.DoctorRequestDto;
import com.org.SpringSecurity.dto.DoctorResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private final DoctorService doctorService;

    @GetMapping("/{id}")
    public DoctorResponseDto getDoctorById(@PathVariable Long id){
        System.out.println("id values is :" + "=" + id);
        return  doctorService.getDoctorById(id);
    }

    @PostMapping("/AddDoctor")
    public DoctorResponseDto AddDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        return  doctorService.AddDoctor(doctorRequestDto);
    }
}
