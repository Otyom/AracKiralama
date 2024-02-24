package com.example.AracKiralama.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetRentalOfficeDetailResponseDto {
   private String officeName;
   private String officeNumber;
   private String rentalCompany;
   private String streetName;
   private String neigborhood;
   private String district;
   private String city;
}
