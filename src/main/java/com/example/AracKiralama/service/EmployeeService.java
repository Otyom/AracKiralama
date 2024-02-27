package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.LoginPersonRequestDto;
import com.example.AracKiralama.dto.request.SaveEmployeeRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.LoginPersonResponseDto;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.Employee;
import com.example.AracKiralama.entity.enums.Role;
import com.example.AracKiralama.entity.rentacar.RentalOffice;
import com.example.AracKiralama.exception.rentacarExceptions.RentalCompanyNotFoundException;
import com.example.AracKiralama.exception.persons.*;
import com.example.AracKiralama.exception.rentacarExceptions.RentalOfficeNotFoundException;
import com.example.AracKiralama.repository.IEmployeRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final IEmployeRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final RentalOfficeService rentalOfficeService;
    private final AdminService adminService;
    public EmployeeService(IEmployeRepository repository, JwtTokenManeger jwtTokenManeger, RentalOfficeService rentalOfficeService, AdminService adminService){
        this.repository=repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.rentalOfficeService = rentalOfficeService;
        this.adminService = adminService;
    }

    public BaseResponseDto saveEmployee(SaveEmployeeRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<RentalOffice> rentalOfficeOptional = rentalOfficeService.findById(dto.getRentalOfficeId());
        if (rentalOfficeOptional.isEmpty()) {
            throw new RentalOfficeNotFoundException();
        }
        if (repository.existsByEmail(dto.getEmail())){
            throw new ExsistByEmail();
        }
        Employee newAdmin=Employee.builder()
                .rol(Role.ROLE_ADMIN)
                .name(dto.getName())
                .surname(dto.getSurname())
                .adres(dto.getAdres())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .profession(dto.getProfession())
                .rentalOffice(rentalOfficeOptional.get())
                .salary(dto.getSalary())
                .build();
        repository.save(newAdmin);
        return BaseResponseDto.builder()
                .statusCode(200)
                .message("Çalışan kayıt edildi")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public LoginPersonResponseDto login(LoginPersonRequestDto dto) {
        if (!repository.existsByEmail(dto.getEmail())){
            throw new EmailNotFoundException();
        }
        Optional<Employee> employee=repository.findOptionalByEmail(dto.getEmail());
        if(!employee.get().getPassword().equals(dto.getPassword())){
            throw new PasswordNotMatch();
        }

        Optional<String> token=jwtTokenManeger.createToken(employee.get().getId(),employee.get().getRol());
        return LoginPersonResponseDto.builder()
                .message("Başarılı giriş yapıldı.")
                .statusCode(200)
                .token(token.get())
                .build();
    }


    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

}
