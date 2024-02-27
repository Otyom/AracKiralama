package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveDistrictRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("district")
public class DistrictController {
    private final DistrictService districtService;
    public DistrictController(DistrictService districtService){
        this.districtService=districtService;
    }

    @PostMapping("/saveDistrict")
    public ResponseEntity<BaseResponseDto>saveDistrict(@RequestBody SaveDistrictRequestDto dto){
        return ResponseEntity.ok(districtService.saveDistrict(dto));
    }
}
