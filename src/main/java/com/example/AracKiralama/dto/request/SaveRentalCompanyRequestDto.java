package com.example.AracKiralama.dto.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveRentalCompanyRequestDto {
    private String token;
    private String CompanyName;
}
