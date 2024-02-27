package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveCarClassRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.CarClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carClass")
public class CarClassController {
    private final CarClassService carClassService;

    public CarClassController(CarClassService carClassService) {
        this.carClassService = carClassService;
    }



    @PostMapping("/saveCarClass")
    public ResponseEntity<BaseResponseDto> saveCarClass(@RequestBody SaveCarClassRequestDto dto){
        return ResponseEntity.ok(carClassService.saveCarClass(dto));
    }
}
