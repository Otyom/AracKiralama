package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.CarUpdateRequestDto;
import com.example.AracKiralama.dto.request.SaveCarRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.Employee;
import com.example.AracKiralama.entity.rentacar.*;
import com.example.AracKiralama.exception.rentacarExceptions.*;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.repository.ICarRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import com.example.AracKiralama.utility.ServiceManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarService extends ServiceManeger<Car,Long> {
    private final ICarRepository repository;
    private final AdminService adminService;
    private final EmployeeService employeeService;
    private final RentalOfficeService rentalOfficeService;
    private final RentalCompanyService rentalCompanyService;
    private final CarClassService carClassService;
    private final MarkService markService;
    private final ModelService modelService;
    private final JwtTokenManeger jwtTokenManeger;
    public CarService(ICarRepository repository, AdminService adminService, EmployeeService employeeService, RentalOfficeService rentalOfficeService, RentalCompanyService rentalCompanyService, CarClassService carClassService, MarkService markService, ModelService modelService, JwtTokenManeger jwtTokenManeger){
        super(repository);
        this.repository=repository;
        this.adminService = adminService;
        this.employeeService = employeeService;
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

    public BaseResponseDto deleteCar(String token,Long id){
        Optional<Long> adminId = jwtTokenManeger.getIdByToken(token);
        if (adminId.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(adminId.get());
        if (admin.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<Car>car=repository.findById(id);
        if (car.isEmpty())throw new RuntimeException();
        deleteById(id);

        return BaseResponseDto.builder().message("Araba silindi").statusCode(200).build();
    }

    public BaseResponseDto updateCar(CarUpdateRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Admin> admin= adminService.findById(id.get());
        if (admin.isEmpty() ) {
            throw new AdminNotFoundException();
        }

        Optional<Car> car=findById(dto.getCarId());
        if (car.isEmpty()){
            throw new RuntimeException("Araba yok");
        }

        Optional<CarClass>carClass=carClassService.findById(dto.getClasId());
        if (carClass.isEmpty()){
            throw new CarClassNotFoundException();
        }
        Optional<RentalOffice>rentalOffice= rentalOfficeService.findById(dto.getRentalOfficeId());
        if (rentalOffice.isEmpty()){
            throw new RentalOfficeNotFoundException();
        }
        Optional<RentalCompany>rentalCompany=rentalCompanyService.findById(dto.getCompanyId());
        if (rentalCompany.isEmpty()){
            throw  new RentalCompanyNotFoundException();
        }
        car.get().setDailyPrice(dto.getDailyPrice());
        car.get().setCarClass(carClass.get());
        car.get().setColor(dto.getColor());
        car.get().setFuelType(dto.getFuelType());
        car.get().setRentalOffice(rentalOffice.get());
        car.get().setRentalCompany(rentalCompany.get());
        car.get().setStatus(1);
        update(car.get());
        return BaseResponseDto.builder()
                .statusCode(200)
                .message("Araç güncellendi")
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
