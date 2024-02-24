package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveCarRequestDto;
import com.example.AracKiralama.dto.request.SaveCityRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.CarServcie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarServcie carServcie;

    public CarController(CarServcie carServcie) {
        this.carServcie = carServcie;
    }

    @PostMapping("/saveCar")
    public ResponseEntity<BaseResponseDto> saveCar(SaveCarRequestDto dto){
        return ResponseEntity.ok(carServcie.saveCar(dto));
    }


}
