package com.example.AracKiralama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveEmployeeRequestDto {
    private String token;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String adres;
    private String password;
    private Long rentalOfficeId;
    private String profession;
    private double salary;

}
