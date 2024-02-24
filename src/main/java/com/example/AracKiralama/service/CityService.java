package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.SaveCityRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Address.City;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.exception.addressExceptions.ExistsByCityNameException;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.repository.ICityRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    private final ICityRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    public CityService(ICityRepository repository, JwtTokenManeger jwtTokenManeger, AdminService adminService) {
        this.repository = repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
    }


    public BaseResponseDto saveCity(SaveCityRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        if (repository.existsByCityName(dto.getCityName())){
            throw new ExistsByCityNameException();
        }


        City createCity= City.builder()
                .cityName(dto.getCityName())
                .build();
        repository.save(createCity);
        return BaseResponseDto.builder()
                .statusCode(200)
                .message("İl kayıt edildi")
                .httpStatus(HttpStatus.OK)
                .build();
    }




    public Optional<City> findById(Long cityId) {
        Optional<City> city=repository.findById(cityId);
        return city;
    }



}
