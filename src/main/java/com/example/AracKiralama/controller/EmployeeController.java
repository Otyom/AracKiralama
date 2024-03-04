package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.LoginPersonRequestDto;
import com.example.AracKiralama.dto.request.SaveEmployeeRequestDto;
import com.example.AracKiralama.dto.request.UpdatePersonRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.LoginPersonResponseDto;
import com.example.AracKiralama.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }


    @PostMapping("/saveEmployee")
    public ResponseEntity<BaseResponseDto>saveEmployee(@RequestBody SaveEmployeeRequestDto dto){
        return ResponseEntity.ok(employeeService.saveEmployee(dto));
    }

    @PostMapping("/loginEmployee")
    public ResponseEntity<LoginPersonResponseDto> loginEmployee(@RequestBody LoginPersonRequestDto dto){
        return ResponseEntity.ok(employeeService.login(dto));
    }

    @PutMapping("/updateEmploye")
    public ResponseEntity<BaseResponseDto> updateEmploye(@RequestBody UpdatePersonRequestDto dto){
        return ResponseEntity.ok(employeeService.updateEmployee(dto));
    }
}
