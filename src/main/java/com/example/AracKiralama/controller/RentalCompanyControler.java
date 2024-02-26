package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveRentalCompanyRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.RentalCompanyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentalCompany")
public class RentalCompanyControler {
    private final RentalCompanyService rentalCompanyService;

    public RentalCompanyControler(RentalCompanyService rentalCompanyService) {
        this.rentalCompanyService = rentalCompanyService;
    }

    @PostMapping("/saveRentaCompany")
    public ResponseEntity<BaseResponseDto> saveRentalCompany(@RequestBody SaveRentalCompanyRequestDto dto){
        return ResponseEntity.ok(rentalCompanyService.saveRentalCompany(dto));
    }
}
