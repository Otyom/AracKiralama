package com.example.AracKiralama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveRentalRequestDto {
    private String token;
    private Long carId;
    private String startDate;
    private String finishDate;
}
