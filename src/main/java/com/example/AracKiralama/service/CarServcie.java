package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.SaveCarRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.rentacar.*;
import com.example.AracKiralama.exception.rentacarExceptions.*;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.repository.ICarRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServcie {
    private final ICarRepository repository;
    private final AdminService adminService;
    private final RentalOfficeService rentalOfficeService;
    private final RentalCompanyService rentalCompanyService;
    private final CarClassService carClassService;
    private final MarkService markService;
    private final ModelService modelService;
    private final JwtTokenManeger jwtTokenManeger;
    public CarServcie(ICarRepository repository, AdminService adminService, RentalOfficeService rentalOfficeService, RentalCompanyService rentalCompanyService, CarClassService carClassService, MarkService markService, ModelService modelService, JwtTokenManeger jwtTokenManeger){
        this.repository=repository;
        this.adminService = adminService;
        this.rentalOfficeService = rentalOfficeService;
        this.rentalCompanyService = rentalCompanyService;
        this.carClassService = carClassService;
        this.markService = markService;
        this.modelService = modelService;
        this.jwtTokenManeger = jwtTokenManeger;
    }


    public BaseResponseDto saveCar(SaveCarRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<RentalOffice> rentalOffice=rentalOfficeService.findById(dto.getRentalOfficeId());
        if (rentalOffice.isEmpty())throw new RentalOfficeNotFoundException();
        Optional<CarClass>carClass=carClassService.findById(dto.getCarClassId());
        if (carClass.isEmpty())throw new CarClassNotFoundException();
        Optional<Mark>mark=markService.findById(dto.getMarkId());
        if (mark.isEmpty())throw new MarkNotFoundException();
        Optional<Model>model=modelService.findById(dto.getModelId());
        if (model.isEmpty())throw new ModelNotFoundException();
        Optional<RentalCompany>rentalCompany=rentalCompanyService.findById(dto.getRentalComponyId());
        if (rentalCompany.isEmpty())throw new RentalCompanyNotFoundException();

        Car car=Car.builder()
                .carClass(carClass.get())
                .carMark(mark.get())
                .carModel(model.get())
                .rentalCompany(rentalCompany.get())
                .rentalOffice(rentalOffice.get())
                .carPlate(dto.getCarPlate())
                .color(dto.getColor())
                .dailyPrice(dto.getDailyPrice())
                .fuelType(dto.getFuelType())
                .build();
        repository.save(car);
        return BaseResponseDto.builder()
                .message("Araba kayıt edildi")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }

}
