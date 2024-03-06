package com.example.AracKiralama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageSaveRequestDto {
    private String token;
    private Long carId;
    private String imageName;
    private String cloudinaryUr;
}
