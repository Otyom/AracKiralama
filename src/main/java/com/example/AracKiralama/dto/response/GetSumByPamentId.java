package com.example.AracKiralama.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetSumByPamentId {
    private double sum;
    private int statusCode;
    private String message;
    private HttpStatus httpStatus;
}
