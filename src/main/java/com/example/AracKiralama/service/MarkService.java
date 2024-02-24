package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.SaveMarkRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.rentacar.Mark;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.exception.rentacarExceptions.ExistsByModelException;
import com.example.AracKiralama.repository.IMarkRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarkService {
    private final IMarkRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    private MarkService(IMarkRepository repository, JwtTokenManeger jwtTokenManeger, AdminService adminService){
        this.repository=repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
    }



    public BaseResponseDto saveMark(SaveMarkRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        if (repository.existsByMarkName(dto.getMarkName()))throw new ExistsByModelException();

        Mark mark= Mark.builder()
                .markName(dto.getMarkName())
                .build();
        repository.save(mark);
       return BaseResponseDto.builder()
                .httpStatus(HttpStatus.OK)
                .message("Yeni marka eklendi")
                .statusCode(200)
                .build();
    }







    public Optional<Mark>findById(Long id){
        Optional<Mark> mark=repository.findById(id);
        return mark;
    }


}
