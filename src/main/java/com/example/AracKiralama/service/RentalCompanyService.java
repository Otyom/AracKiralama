package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.SaveRentalCompanyRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Address.Neighborhood;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.rentacar.RentalCompany;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.exception.rentacarExceptions.ExistsByCompanyNameException;
import com.example.AracKiralama.exception.rentacarExceptions.ExistsByModelException;
import com.example.AracKiralama.repository.IRentalCompanyRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalCompanyService {
    private final IRentalCompanyRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    public RentalCompanyService (IRentalCompanyRepository repository, JwtTokenManeger jwtTokenManeger, AdminService adminService){
        this.repository=repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
    }



    public BaseResponseDto saveRentalCompany(SaveRentalCompanyRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        if (repository.existsByCompanyName(dto.getCompanyName()))throw new ExistsByCompanyNameException();

        RentalCompany rentalCompany=RentalCompany.builder()
                .companyName(dto.getCompanyName())
                .build();
        repository.save(rentalCompany);

        return BaseResponseDto.builder()
                .httpStatus(HttpStatus.OK)
                .message("Şirket eklendi")
                .statusCode(200)
                .build();
    }


















    Optional<RentalCompany> findById(Long id){
        Optional<RentalCompany> rentalCompany=repository.findById(id);
        return rentalCompany;
    }
}
