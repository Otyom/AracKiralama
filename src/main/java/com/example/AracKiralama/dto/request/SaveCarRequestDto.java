package com.example.AracKiralama.dto.request;

import com.example.AracKiralama.entity.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveCarRequestDto {
    private String token;
    private Long carClassId;
    private Long markId;
    private Long modelId;
    private Long rentalComponyId;
    private Long rentalOfficeId;
    private String color;
    private String carPlate;
    private FuelType fuelType;
    private Double dailyPrice;

}
