package com.example.AracKiralama.dto.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveStreetRequestDto {
    private String token;
    private Long neigborhoodId;
    private String streetName;

}
