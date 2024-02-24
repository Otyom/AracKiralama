package com.example.AracKiralama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveNeighborhoodRequestDto {
    private String token;
    private Long districtId;
    private String neighborhoodName;

}
