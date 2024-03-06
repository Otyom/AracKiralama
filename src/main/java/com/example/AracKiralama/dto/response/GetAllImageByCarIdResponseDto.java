package com.example.AracKiralama.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllImageByCarIdResponseDto {
    private String dosyaAdi;
    private String cloudinaryUrl;
    private Long carId;
}
