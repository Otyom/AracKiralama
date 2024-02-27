package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveStreetRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.StreetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/street")
public class StreetController {
    private  final StreetService streetService;

    public StreetController(StreetService streetService){
        this.streetService=streetService;
    }


    @PostMapping("/saveStreet")
    public ResponseEntity<BaseResponseDto> saveStreet(@RequestBody SaveStreetRequestDto dto){
        return ResponseEntity.ok(streetService.saveStreet(dto));
    }
}
