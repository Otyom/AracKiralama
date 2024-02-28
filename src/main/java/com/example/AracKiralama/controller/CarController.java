package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.CarUpdateRequestDto;
import com.example.AracKiralama.dto.request.SaveCarRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.GetAllCarByOfficeIdResponseDto;
import com.example.AracKiralama.dto.response.GetAllCarResponseDto;
import com.example.AracKiralama.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carServcie;

    public CarController(CarService carServcie) {
        this.carServcie = carServcie;
    }

    @PostMapping("/saveCar")
    public ResponseEntity<BaseResponseDto> saveCar(@RequestBody SaveCarRequestDto dto){
        return ResponseEntity.ok(carServcie.saveCar(dto));
    }

    @DeleteMapping("/{token}/{silinecekId}")
    public ResponseEntity<BaseResponseDto> deleteCar(@PathVariable("token")String token,@PathVariable("silinecekId")Long id){
        return ResponseEntity.ok(carServcie.deleteCar(token,id));
    }

    @PutMapping("/updateCar")
    public ResponseEntity<BaseResponseDto>updateCar(CarUpdateRequestDto dto){
        return ResponseEntity.ok(carServcie.updateCar(dto));
    }
    @GetMapping("/getCarByOfficeId")
    public ResponseEntity<List<GetAllCarByOfficeIdResponseDto>> getCarByOfficeId(@RequestParam String token, @RequestParam Long rentalOfficeId){
        return ResponseEntity.ok(carServcie.getAllByRentalOfficeId(token,rentalOfficeId));
    }

    @GetMapping("/getAllCar")
    public ResponseEntity<List<GetAllCarResponseDto>> getAllCar(@RequestParam String token){
        return ResponseEntity.ok(carServcie.getAllCar(token));
    }



}
