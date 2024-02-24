package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.SaveNeighborhoodRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Address.District;
import com.example.AracKiralama.entity.Address.Neighborhood;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.exception.addressExceptions.DistrictNotFoundException;
import com.example.AracKiralama.exception.addressExceptions.ExistsByNeighborhoodNameException;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.repository.INeighborhoodRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NeigborhoodService {
    private final INeighborhoodRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;
    private final DistrictService districtService;
    public NeigborhoodService(INeighborhoodRepository repository, JwtTokenManeger jwtTokenManeger, AdminService service, DistrictService districtService){
        this.repository=repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = service;
        this.districtService = districtService;
    }


    public BaseResponseDto saveNeighborhood(SaveNeighborhoodRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<District> district= districtService.findById(dto.getDistrictId());
        if (district.isEmpty()){
            throw new DistrictNotFoundException();
        }
        if (repository.existsByDistrictAndNeighborhoodName(district.get(), dto.getNeighborhoodName())) {
            throw new ExistsByNeighborhoodNameException();
        }
        Neighborhood neighborhood=Neighborhood.builder()
                .neighborhoodName(dto.getNeighborhoodName())
                .district(district.get())
                .build();
        repository.save(neighborhood);

        return BaseResponseDto.builder()
                .statusCode(200)
                .message("Mahalle kayıt edildi")
                .httpStatus(HttpStatus.OK)
                .build();
    }






















    public Optional<Neighborhood>findById(Long id){
        return repository.findById(id);
    }
}
