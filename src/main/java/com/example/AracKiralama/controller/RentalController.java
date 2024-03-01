package com.example.AracKiralama.controller;


import com.example.AracKiralama.dto.request.FinishRentalPaymentRequestDto;
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
    @PutMapping("/startRental")
    public ResponseEntity<BaseResponseDto> startRental(@RequestBody FinishRentalPaymentRequestDto dto){
        return ResponseEntity.ok(rentalService.startRental(dto));
    }


    @PutMapping("/finishRental")
    public ResponseEntity<BaseResponseDto>finishRental(@RequestBody FinishRentalRequestDto dto){
        return ResponseEntity.ok(rentalService.finishRental(dto));
    }
}
