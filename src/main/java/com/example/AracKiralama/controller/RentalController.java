package com.example.AracKiralama.controller;


import com.example.AracKiralama.dto.request.FinishRentalRequestDto;
import com.example.AracKiralama.dto.request.SaveRentalRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")
public class RentalController {
   private final RentalService rentalService;
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/createRental")
    public ResponseEntity<BaseResponseDto>createRental(@RequestBody SaveRentalRequestDto dto){
        return ResponseEntity.ok(rentalService.createRental(dto));
    }
    @PutMapping("/finishPayment")
    public ResponseEntity<BaseResponseDto> finishRentalll(@RequestBody FinishRentalRequestDto dto){
        return ResponseEntity.ok(rentalService.finishRental(dto));
    }
}
