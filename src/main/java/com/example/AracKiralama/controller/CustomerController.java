package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.LoginPersonRequestDto;
import com.example.AracKiralama.dto.request.SaveCustomerRequestDto;
import com.example.AracKiralama.dto.request.UpdateCustomerRequestDto;
import com.example.AracKiralama.dto.request.UpdatePersonRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.LoginPersonResponseDto;
import com.example.AracKiralama.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }


    @PostMapping("/save")
    public ResponseEntity<BaseResponseDto>saveCustomer(@RequestBody SaveCustomerRequestDto dto){
        return  ResponseEntity.ok(customerService.saveCustomer(dto));
    }

    @PostMapping("/loginCustomer")
    public ResponseEntity<LoginPersonResponseDto>loginCustomer(@RequestBody LoginPersonRequestDto dto){
        return ResponseEntity.ok(customerService.login(dto));
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<BaseResponseDto>updateCustomer(@RequestBody UpdateCustomerRequestDto dto){
        return ResponseEntity.ok(customerService.updateCustomer(dto));
    }

}
