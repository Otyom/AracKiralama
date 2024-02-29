package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.SaveRentalOfficeRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.GetRentalOfficeDetailResponseDto;
import com.example.AracKiralama.entity.Address.Street;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.rentacar.RentalCompany;
import com.example.AracKiralama.entity.rentacar.RentalOffice;
import com.example.AracKiralama.exception.addressExceptions.StreetNotFoundException;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.exception.rentacarExceptions.RentalCompanyNotFoundException;
import com.example.AracKiralama.exception.addressExceptions.StreetIsEmptyException;
import com.example.AracKiralama.exception.rentacarExceptions.InvalidPhoneNumberException;
import com.example.AracKiralama.exception.rentacarExceptions.RentalOfficeNotFoundException;
import com.example.AracKiralama.repository.IRentalOfficeRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalOfficeService {
    private final IRentalOfficeRepository repository;
    private final StreetService streetService;
    private final RentalCompanyService rentalCompanyService;
    private final JwtTokenManeger jwtTokenManeger;
    private final AdminService adminService;

    public RentalOfficeService(IRentalOfficeRepository repository, StreetService streetService, RentalCompanyService rentalCompanyService, JwtTokenManeger jwtTokenManeger, AdminService adminService){
        this.repository=repository;
        this.streetService = streetService;
        this.rentalCompanyService = rentalCompanyService;
        this.jwtTokenManeger = jwtTokenManeger;
        this.adminService = adminService;
    }

    public GetRentalOfficeDetailResponseDto getRentalOfficesDetails(Long rentalOfficeId,String token) {
        Optional<Long> id = jwtTokenManeger.getIdByToken(token);
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<RentalOffice> rentalOffice = repository.findRentalOfficeWithDetailsById(rentalOfficeId);
        if (rentalOffice.isEmpty())throw new RentalOfficeNotFoundException();

        String neigborhoodName=rentalOffice.get().getStreet().getNeighborhood().getNeighborhoodName();
        String districtName=rentalOffice.get().getStreet().getNeighborhood().getDistrict().getDistrictName();
        String cityName=rentalOffice.get().getStreet().getNeighborhood().getDistrict().getCity().getCityName();

        return GetRentalOfficeDetailResponseDto.builder()
                .officeName(rentalOffice.get().getOfficeName())
                .officeNumber(rentalOffice.get().getOfficePhoneNumber())
                .rentalCompany(rentalOffice.get().getRentalCompany().getCompanyName())
                .streetName(rentalOffice.get().getStreet().getStreetName())
                .neigborhood(neigborhoodName)
                .district(districtName)
                .city(cityName)
                .build();
    }

    public BaseResponseDto saveRentalOffice(SaveRentalOfficeRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<Street> street = streetService.findById(dto.getStreetId());
        Optional<RentalCompany> rentalCompany=rentalCompanyService.findById(dto.getRentalCompanyId());

        if (street.isEmpty()) throw new StreetNotFoundException();
        if (rentalCompany.isEmpty())throw new RentalCompanyNotFoundException();
        if (!(dto.getOfficeNumber().length() ==10))throw new InvalidPhoneNumberException();

        RentalOffice rentalOffice=RentalOffice.builder()
                .rentalCompany(rentalCompany.get())
                .officeName(dto.getOfficeName())
                .officePhoneNumber(dto.getOfficeNumber())
                .street(street.get())
                .build();
        repository.save(rentalOffice);

        return BaseResponseDto.builder()
                .message("Yeni araç kiralama ofisi eklendi.")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    public Optional<RentalOffice> findById(Long rentalOfficeId) {
        Optional<RentalOffice> rentalOffice=repository.findById(rentalOfficeId);
        return rentalOffice;
    }
}
