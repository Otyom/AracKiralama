package com.example.AracKiralama.controller;


import com.example.AracKiralama.dto.request.ImageSaveRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.GetAllImageByCarIdResponseDto;
import com.example.AracKiralama.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ımage")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @PostMapping("/addImage")
    public ResponseEntity<BaseResponseDto> addImage(@RequestBody ImageSaveRequestDto dto) {
    return ResponseEntity.ok(imageService.addImage(dto));
    }
    @GetMapping("/getAllImageBycarId")
    public ResponseEntity<List<GetAllImageByCarIdResponseDto>>getAllImageBycarId(@RequestParam String token, Long id){
        return ResponseEntity.ok(imageService.getAllImageBycarId(token,id));
    }

}
