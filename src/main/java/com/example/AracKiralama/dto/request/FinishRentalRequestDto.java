package com.example.AracKiralama.dto.request;

import com.example.AracKiralama.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinishRentalRequestDto {
    private String token;
    private Long customerId;
    private Long paymentId;
}
