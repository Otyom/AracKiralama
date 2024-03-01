package com.example.AracKiralama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinishRentalPaymentRequestDto {
    private String token;
    private Long customerId;
    private Long paymentId;
}
