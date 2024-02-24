package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.SaveDistrictRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Address.City;
import com.example.AracKiralama.entity.Address.District;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.exception.addressExceptions.CityNotFoundException;
import com.example.AracKiralama.exception.addressExceptions.ExistsByDistrictNameException;
import com.example.AracKiralama.exception.addressExceptions.ExistsByNeighborhoodNameException;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.repository.ICityRepository;
import com.example.AracKiralama.repository.IDistrictRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistrictService {
    private final IDistrictRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    private final CityService cityService;
    public DistrictService(IDistrictRepository repository, JwtTokenManeger jwtTokenManeger, AdminService adminService, CityService cityService) {
        this.repository = repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
        this.cityService = cityService;
    }




    public BaseResponseDto saveDistrict(SaveDistrictRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<City>city=cityService.findById(dto.getCityId());
        if (city.isEmpty()){
            throw new CityNotFoundException();
        }if (repository.existsByCityAndDistrictName(city.get(),dto.getDistrictName())){
            throw new ExistsByDistrictNameException();
        }
        District district= District.builder()
                .districtName(dto.getDistrictName())
                .city(city.get())
                .build();
        repository.save(district);
        return BaseResponseDto.builder()
                .httpStatus(HttpStatus.OK)
                .message("ilçe kayıt edildi")
                .statusCode(200)
                .build();

    }




    public Optional<District> findById(Long id) {
        Optional<District> district=repository.findById(id);
        return district;
    }
}
