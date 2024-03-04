package com.example.AracKiralama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCustomerRequestDto {
    private String token;
    private String email;
    private String password;
    private String phoneNumber;
    private String adress;
}
