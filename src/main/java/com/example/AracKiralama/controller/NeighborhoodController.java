package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveNeighborhoodRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.NeigborhoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/neighborhood")
public class NeighborhoodController {
    private final NeigborhoodService neigborhoodService;

    public NeighborhoodController(NeigborhoodService neigborhoodService) {
        this.neigborhoodService = neigborhoodService;
    }

    @PostMapping("save")
    public ResponseEntity<BaseResponseDto>saveNeigborhood(@RequestBody SaveNeighborhoodRequestDto dto){
        return ResponseEntity.ok(neigborhoodService.saveNeighborhood(dto));
    }


}
