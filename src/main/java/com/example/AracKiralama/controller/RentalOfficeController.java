package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveRentalOfficeRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.GetRentalOfficeDetailResponseDto;
import com.example.AracKiralama.service.RentalOfficeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentalOffice")
@AllArgsConstructor
public class RentalOfficeController {

    @Autowired
    private RentalOfficeService rentalOfficeService;

    @GetMapping("/details/{rentalOfficeId}")
    public ResponseEntity<GetRentalOfficeDetailResponseDto> getRentalOfficesDetails(@PathVariable Long rentalOfficeId) {
        return ResponseEntity.ok(rentalOfficeService.getRentalOfficesDetails(rentalOfficeId));
    }



    @PostMapping("/saveRentalOffice")
    public ResponseEntity<BaseResponseDto>saveRentalOffice(SaveRentalOfficeRequestDto dto){
        return ResponseEntity.ok(rentalOfficeService.saveRentalOffice(dto));
    }







}
