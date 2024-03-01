package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SavePaymentRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.GetSumByPamentId;
import com.example.AracKiralama.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getPayment")
    public ResponseEntity<GetSumByPamentId>sumByPamentId(String token,@RequestParam Long id){
        return ResponseEntity.ok(paymentService.sumPayment(token,id));
    }

    @PostMapping("/createPayment")
    public ResponseEntity<BaseResponseDto>newPayment(@RequestBody SavePaymentRequestDto dto){
        return ResponseEntity.ok(paymentService.createPayment(dto));
    }


}
