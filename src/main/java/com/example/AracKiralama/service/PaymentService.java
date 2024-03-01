package com.example.AracKiralama.service;


import com.example.AracKiralama.dto.request.SavePaymentRequestDto;
import com.example.AracKiralama.dto.response.BaseResponseDto;
import com.example.AracKiralama.dto.response.GetSumByPamentId;
import com.example.AracKiralama.entity.Customer;
import com.example.AracKiralama.entity.rentacar.Car;
import com.example.AracKiralama.entity.rentacar.Payment;
import com.example.AracKiralama.entity.rentacar.Rental;
import com.example.AracKiralama.exception.persons.AdminNotFoundException;
import com.example.AracKiralama.exception.persons.InvaildToken;
import com.example.AracKiralama.repository.IPaymentRepository;
import com.example.AracKiralama.utility.JwtTokenManeger;
import com.example.AracKiralama.utility.ServiceManeger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Optional;

import static java.time.Period.between;

@Service
public class PaymentService extends ServiceManeger<Payment,Long> {
    private final IPaymentRepository repository;
    private final JwtTokenManeger jwtTokenManeger;
    private final CustomerService customerService;
    private final CarService carService;
    private final RentalService rentalService;

    public PaymentService(IPaymentRepository repository, JwtTokenManeger jwtTokenManeger, CustomerService customerService, CarService carService, RentalService rentalService) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManeger = jwtTokenManeger;
        this.customerService = customerService;
        this.carService = carService;
        this.rentalService = rentalService;
    }

    public BaseResponseDto createPayment(SavePaymentRequestDto dto){
        Optional<Long> id = jwtTokenManeger.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Customer> customer= customerService.findById(id.get());
        if (customer.isEmpty()) {
            throw new AdminNotFoundException();
        }

        Optional<Car> car=carService.findById(dto.getCarId());
        if (car.isEmpty())throw new RuntimeException();
        Optional<Rental> rental=rentalService.findById(dto.getRentalId());
        if (rental.isEmpty())throw new RuntimeException();
        rental=rentalService.findByCustomerId(id.get());
        if (rental.isEmpty())throw new RuntimeException();
        String startDateString = rental.get().getStartDate();
        String finishDateString = rental.get().getFinishDate();


        // DateTimeFormatter ile stringleri LocalDate'e çevirme
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate finishDate = LocalDate.parse(finishDateString, formatter);

        // ChronoUnit.DAYS.between() metoduyla gün farkını alma.
        long dayDifference = ChronoUnit.DAYS.between(startDate, finishDate);

        System.out.println("Gün Farkı: " + dayDifference);
        double summ = dayDifference * car.get().getDailyPrice();
        System.out.println("toplam tutar:"+summ);
        if (!(rental.get().getPaymentId()==null))throw new RuntimeException();

        Payment payment = Payment.builder()
                .paymentDate(LocalDateTime.now().toString())
                .carId(car.get().getId())
                .sum(summ)
                .cardHolderName(dto.getCardHolderName())
                .carId(dto.getCarId())
                .cvv(dto.getCvv())
                .expirationDate(dto.getExpirationDate())
                .customerId(dto.getCustomerId())
                .build();
        save(payment);
        return BaseResponseDto.builder()
                .httpStatus(HttpStatus.OK)
                .statusCode(200)
                .message("Ödemeniz alınmıştır. toplam çekilen tutar: "+payment.getSum().toString())
                .build();
    }

    public GetSumByPamentId sumPayment(String token, Long paymentId){
        Optional<Long> id = jwtTokenManeger.getIdByToken(token);
        if (id.isEmpty()) {
            throw new InvaildToken();
        }
        Optional<Customer> customer= customerService.findById(id.get());
        if (customer.isEmpty()) {
            throw new AdminNotFoundException();
        }
        Optional<Payment>payment=repository.findById(paymentId);
        if (payment.isEmpty())throw new RuntimeException();

        return GetSumByPamentId.builder()
                .sum(payment.get().getSum())
                .statusCode(200)
                .message("Toplam ödemeniz: "+payment.get().getSum()+" Tl'dir")
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
