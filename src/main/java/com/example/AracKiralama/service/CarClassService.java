package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.SaveCarClassRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.rentacar.CarClass;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.exception.rentacarExceptions.ExistsByMarkNameException;
import com.example.AracKiralama.repository.ICarClassRepositorty;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarClassService {
    private final ICarClassRepositorty repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    public CarClassService(ICarClassRepositorty repository, JwtTokenManeger jwtTokenManeger, AdminService adminService){
        this.repository=repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
    }

    public Optional<CarClass> findById(Long id) {
        Optional<CarClass> carClass=repository.findById(id);
        return carClass;
    }

    public BaseResponseDto saveCarClass(SaveCarClassRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        if (repository.existsByClasName(dto.getClasName()))throw new ExistsByMarkNameException();


        CarClass carClass=CarClass.builder()
                .clasName(dto.getClasName())
                .build();
        repository.save(carClass);

        return BaseResponseDto.builder()
                .statusCode(200)
                .message("Yeni araba sınıfı eklendi")
                .httpStatus(HttpStatus.OK)
                .build();
    }

}
