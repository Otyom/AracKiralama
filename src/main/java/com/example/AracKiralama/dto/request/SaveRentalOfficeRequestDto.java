package com.example.AracKiralama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveRentalOfficeRequestDto {
    private String token;
    private String officeName;
    private Long rentalCompanyId;
    private Long streetId;
    private String officeNumber;
}
