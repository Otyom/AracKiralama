package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.LoginPersonRequestDto;
import com.example.AracKiralama.dto.request.SaveCustomerRequestDto;
import com.example.AracKiralama.dto.request.UpdateCustomerRequestDto;
import com.example.AracKiralama.dto.request.UpdatePersonRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.LoginPersonResponseDto;
import com.example.AracKiralama.entity.Customer;
import com.example.AracKiralama.entity.enums.Role;
import com.example.AracKiralama.exception.persons.*;
import com.example.AracKiralama.repository.ICustomerRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import com.example.AracKiralama.utility.ServiceManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService extends ServiceManeger<Customer,Long> {
    private final ICustomerRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    public CustomerService(ICustomerRepository repository, JwtTokenManeger jwtTokenManeger, AdminService adminService){
        super(repository);
        this.repository=repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
    }

    public BaseResponseDto saveCustomer(SaveCustomerRequestDto dto){
        if (repository.existsByEmail(dto.getEmail())){
            throw new ExsistByEmail();
        }
        Customer newCustomer=Customer.builder()
                .rol(Role.ROLE_CUSTOMER)
                .name(dto.getName())
                .surname(dto.getSurname())
                .adres(dto.getAdres())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
        repository.save(newCustomer);
        return BaseResponseDto.builder()
                .statusCode(200)
                .message("Kullanıcı kaydınız oluşturulmuştur")
                .httpStatus(HttpStatus.OK)
                .build();
    }


    public LoginPersonResponseDto login(LoginPersonRequestDto dto) {
        if (!repository.existsByEmail(dto.getEmail())){
            throw new EmailNotFoundException();
        }
        Optional<Customer> customer=repository.findOptionalByEmail(dto.getEmail());
        if(!customer.get().getPassword().equals(dto.getPassword())){
            throw new PasswordNotMatch();
        }

        Optional<String> token=jwtTokenManeger.createToken(customer.get().getId(),customer.get().getRol());
        return LoginPersonResponseDto.builder()
                .message("Başarılı giriş yapıldı")
                .statusCode(200)
                .token(token.get())
                .build();
    }


    public BaseResponseDto updateCustomer(UpdateCustomerRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
            Optional<Customer> customer= repository.findById(id.get());
        if (customer.isEmpty()) {
            throw new AdminNotFoundException();
        }


        customer.get().setEmail(dto.getEmail());
        customer.get().setPassword(dto.getPassword());
        customer.get().setAdres(dto.getAdress());
        customer.get().setPhone(dto.getPhoneNumber());
        update(customer.get());
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).statusCode(200).message("Kullanıcı bilgileri güncellendi").build();

    }

    public Optional<Customer> findById(Long id) {
       Optional<Customer>customer= repository.findById(id);
        return customer;
    }
}
