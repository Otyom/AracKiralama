package com.example.AracKiralama.entity.rentacar;

import com.example.AracKiralama.entity.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private RentalCompany rentalCompany;

    @ManyToOne
    @JoinColumn(name = "rentalOfficeId",referencedColumnName = "id")
    private RentalOffice rentalOffice;

    @ManyToOne
    @JoinColumn(name = "clasId", referencedColumnName = "id")
    private CarClass carClass;

    @ManyToOne
    @JoinColumn(name = "markId", referencedColumnName = "id")
    private Mark carMark;

    @ManyToOne
    @JoinColumn(name = "modelId", referencedColumnName = "id")
    private Model carModel;


    private String color;
    private String carPlate;
    private FuelType fuelType;
    private String dailyPrice;
}
