package com.example.AracKiralama.service;

import com.example.AracKiralama.dto.request.FinishRentalPaymentRequestDto;
import com.example.AracKiralama.dto.request.FinishRentalRequestDto;
import com.example.AracKiralama.dto.request.SaveRentalRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.entity.Customer;
import com.example.AracKiralama.entity.enums.Status;
import com.example.AracKiralama.entity.rentacar.Car;
import com.example.AracKiralama.entity.rentacar.Rental;
import com.example.AracKiralama.entity.rentacar.RentalOffice;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.repository.IRentalRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import com.example.AracKiralama.utility.ServiceManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalService extends ServiceManeger<Rental,Long> {
    private final IRentalRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final CustomerService customerService;
    private final RentalOfficeService rentalOfficeService;
    private final CarService carService;
    public RentalService(IRentalRepository repository, JwtTokenManeger jwtTokenManeger, CustomerService customerService, RentalOfficeService rentalOfficeService, CarService carService){
        super(repository);
        this.repository=repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.customerService = customerService;
        this.rentalOfficeService = rentalOfficeService;
        this.carService = carService;
    }



    public BaseResponseDto createRental(SaveRentalRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Customer> customer= customerService.findById(id.get());
        if (customer.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<Car>car=carService.findById(dto.getCarId());
        if (car.isEmpty())throw new RuntimeException();
        if (car.get().getStatus()==Status.INACTIVE)throw new RuntimeException();

        Rental rental=Rental.builder()
                .carId(dto.getCarId())
                .customerId(id.get())
                .startDate(dto.getStartDate())
                .finishDate(dto.getFinishDate())
                .build();
        save(rental);
        return BaseResponseDto.builder()
                .message("Araç kiralam işlemi tamamlanmıştır."+"\n Ödeme adımını tamamladıktan sonra(/createPayment) Araç kiralama tamamla adımına geçiniz!")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public BaseResponseDto startRental(FinishRentalPaymentRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Customer> customer= customerService.findById(id.get());
        if (customer.isEmpty()) {
            throw new AdminNotFoundException();
        }

        Optional<Rental> rentall=repository.findByCustomerId(id.get());
        if (rentall.isEmpty())throw new RuntimeException();
        Optional<Car>car=carService.findById(rentall.get().getCarId());
        if (car.isEmpty())throw new RuntimeException();

        rentall.get().setPaymentId(dto.getPaymentId());
        rentall.get().setStatus(Status.ACTIVE);
        update(rentall.get());
        car.get().setStatus(Status.INACTIVE);
        carService.update(car.get());

        return BaseResponseDto.builder()
                .httpStatus(HttpStatus.OK)
                .statusCode(200)
                .message("Aracınız kiralanmıştır. Teslim için ofisimize geliniz.")
                .build();
    }


    public BaseResponseDto finishRental(FinishRentalRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Customer> customer= customerService.findById(id.get());
        if (customer.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<Rental> rentall=repository.findByCustomerId(id.get());
        if (rentall.isEmpty())throw new RuntimeException();
        Optional<Car>car=carService.findById(rentall.get().getCarId());
        if (car.isEmpty())throw new RuntimeException();
        Optional<RentalOffice>rentalOffice=rentalOfficeService.findById(dto.getOfficeId());
        if (rentalOffice.isEmpty())throw new RuntimeException();

        car.get().setStatus(Status.ACTIVE);
        car.get().setRentalOffice(rentalOffice.get());
        carService.update(car.get());

        rentall.get().setStatus(Status.INACTIVE);
        update(rentall.get());

        return BaseResponseDto.builder()
                .message("Araç teslim edilmiştir.")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }




    public Optional<Rental> findByCustomerId(Long id) {
        return repository.findByCustomerId(id);
    }
}
