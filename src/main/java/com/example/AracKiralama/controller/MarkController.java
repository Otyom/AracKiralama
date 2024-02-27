package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveMarkRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.MarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mark")
public class MarkController {
    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping("/saveMark")
    public ResponseEntity<BaseResponseDto>saveMark(@RequestBody SaveMarkRequestDto dto){
        return ResponseEntity.ok(markService.saveMark(dto));
    }
}
