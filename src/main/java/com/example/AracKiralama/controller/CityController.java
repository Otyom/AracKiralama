package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveCityRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;
    public CityController(CityService cityService){
        this.cityService=cityService;
    }

    @PostMapping("/saveCity")
    public ResponseEntity<BaseResponseDto>saveCity(@RequestBody SaveCityRequestDto dto){
        return ResponseEntity.ok(cityService.saveCity(dto));
    }


}
