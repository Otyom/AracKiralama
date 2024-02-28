package com.example.AracKiralama.dto.response;

import com.example.AracKiralama.entity.enums.FuelType;
import com.example.AracKiralama.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class GetAllCarResponseDto {
    private Long carId;
    private String color;
    private String carPlate;
    private FuelType fuelType;
    private Double dailyPrice;
    private Long clasId;
    private Status status;
    private String markName;
    private String modelName;
}
