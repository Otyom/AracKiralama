package com.example.AracKiralama.controller;

import com.example.AracKiralama.dto.request.SaveMarkRequestDto;
import com.example.AracKiralama.dto.request.SaveModelRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.service.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }


    @PostMapping("/saveModel")
      public ResponseEntity<BaseResponseDto> saveModel(@RequestBody SaveModelRequestDto dto){
        return ResponseEntity.ok(modelService.saveModel(dto));
    }

}
