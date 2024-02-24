package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.LoginPersonRequestDto;
import com.example.AracKiralama.dto.request.SaveAdminRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.LoginPersonResponseDto;
import com.example.AracKiralama.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/saveAdmin")
    public ResponseEntity<BaseResponseDto>saveAdmin(@RequestBody SaveAdminRequestDto dto){
        return ResponseEntity.ok(adminService.saveAdmin(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginPersonResponseDto>loginAdmin(@RequestBody LoginPersonRequestDto dto){
        return ResponseEntity.ok(adminService.login(dto));
    }



}
