package com.example.AracKiralama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePersonRequestDto {
    private String token;
    private Long personId;
    private String email;
    private String password;
    private String phoneNumber;
    private String adress;
    private double salary;
    private String profession;
}
