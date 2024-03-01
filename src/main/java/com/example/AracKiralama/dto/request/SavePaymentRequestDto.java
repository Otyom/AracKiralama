package com.example.AracKiralama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SavePaymentRequestDto {
    private String token;
    private Long carId;
    private Long customerId;
    private Long rentalId;
    private String cardNumber;
    private String cardHolderName;
    private Date expirationDate;
    private String cvv;
}
