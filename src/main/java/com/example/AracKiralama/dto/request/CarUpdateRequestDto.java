package com.example.AracKiralama.dto.request;

import com.example.AracKiralama.entity.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarUpdateRequestDto {
    private String token;
    private Long carId;
    private String color;
    private String carPlate;
    private FuelType fuelType;
    private Double dailyPrice;
    private Long rentalOfficeId;
    private Long companyId;
    private Long clasId;
}
