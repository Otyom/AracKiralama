package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.LoginPersonRequestDto;
import com.example.AracKiralama.dto.request.SaveAdminRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.LoginPersonResponseDto;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.enums.Role;
import com.example.AracKiralama.exception.persons.*;
import com.example.AracKiralama.repository.IAdminRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import com.example.AracKiralama.utility.ServiceManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService extends ServiceManeger<Admin,Long> {
    private IAdminRepository repository;
    private JwtTokenManeger jwtTokenManeger;
    public AdminService(IAdminRepository repository,JwtTokenManeger jwtTokenManeger){
        super(repository);
        this.repository=repository;
        this.jwtTokenManeger=jwtTokenManeger;
    }


    public BaseResponseDto saveAdmin(SaveAdminRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= repository.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }

        if (repository.existsByEmail(dto.getEmail())){
            throw new ExsistByEmail();
        }
        Admin newAdmin=Admin.builder()
                .rol(Role.ROLE_ADMIN)
                .name(dto.getName())
                .surname(dto.getSurname())
                .adres(dto.getAdres())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
        repository.save(newAdmin);
        return BaseResponseDto.builder()
                .statusCode(200)
                .message("Admin kayıt edildi")
                .httpStatus(HttpStatus.OK)
                .build();
    }


    public LoginPersonResponseDto login(LoginPersonRequestDto dto) {
        if (!repository.existsByEmail(dto.getEmail())){
            throw new EmailNotFoundException();
        }
        Optional<Admin> admin=repository.findOptionalByEmail(dto.getEmail());
        if(!admin.get().getPassword().equals(dto.getPassword())){
            throw new PasswordNotMatch();
        }

        Optional<String> token=jwtTokenManeger.createToken(admin.get().getId(),admin.get().getRol());
        return LoginPersonResponseDto.builder()
                .message("Başarılı giriş yapıldı")
                .statusCode(200)
                .token(token.get())
                .build();
    }


    public Optional<Admin> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Admin> findOptionalByEmail(String email) {
        return repository.findOptionalByEmail(email);
    }
}
