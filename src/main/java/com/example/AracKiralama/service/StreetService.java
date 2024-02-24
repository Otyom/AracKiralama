package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.SaveStreetRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Address.Neighborhood;
import com.example.AracKiralama.entity.Address.Street;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.exception.addressExceptions.ExistsByStreetNameException;
import com.example.AracKiralama.exception.addressExceptions.NeighborhoodNotFoundException;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.repository.IStreetRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StreetService {
    private final IStreetRepository repository;
    private final NeigborhoodService neigborhoodService;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    public StreetService(IStreetRepository repository, NeigborhoodService neigborhoodService, JwtTokenManeger jwtTokenManeger, AdminService adminService) {
        this.repository = repository;
        this.neigborhoodService=neigborhoodService;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
    }


    public BaseResponseDto saveStreet(SaveStreetRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<Neighborhood> neighborhood= neigborhoodService.findById(dto.getNeigborhoodId());
        if (neighborhood.isEmpty()){
            throw new NeighborhoodNotFoundException();
        }
        if (repository.existsByNeighborhoodAndStreetName(neighborhood.get(), dto.getStreetName())) {
            throw new ExistsByStreetNameException();
        }
            Street street=Street.builder()
                .streetName(dto.getStreetName())
                .neighborhood(neighborhood.get())
                .build();
        repository.save(street);

        return BaseResponseDto.builder()
                .statusCode(200)
                .message("Sokak kayıt edildi")
                .httpStatus(HttpStatus.OK)
                .build();
    }


    public Optional<Street> findById(Long id) {
        Optional<Street> street=repository.findById(id);
        return street;
    }

}
