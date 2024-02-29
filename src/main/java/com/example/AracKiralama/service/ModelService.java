package com.example.AracKiralama.service;


import com.example.AracKiralama.dto.request.SaveModelRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.rentacar.Model;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.exception.rentacarExceptions.ExistsByClasNameException;
import com.example.AracKiralama.exception.rentacarExceptions.ExistsByModelException;
import com.example.AracKiralama.exception.rentacarExceptions.MarkNotFoundException;
import com.example.AracKiralama.repository.IModelRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModelService {
    private final IModelRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    private final MarkService markService;
    public ModelService(IModelRepository repository, JwtTokenManeger jwtTokenManeger, AdminService adminService, MarkService markService){
        this.repository=repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
        this.markService = markService;
    }


    public BaseResponseDto saveModel(SaveModelRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        if (repository.existsByModelName(dto.getModelName()))throw new ExistsByModelException();
        if (markService.findById(dto.getMarkId()).isEmpty())throw new MarkNotFoundException();

        Model model= Model.builder()
                .modelName(dto.getModelName())
                .markId(dto.getMarkId())
                .build();
        repository.save(model);
        return BaseResponseDto.builder()
                .statusCode(200)
                .message("Yeni model eklendi")
                .httpStatus(HttpStatus.OK)
                .build();
    }







    public Optional<Model>findById(Long id){
        Optional<Model> model=repository.findById(id);
        return model;
    }


}
