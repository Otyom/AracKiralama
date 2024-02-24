package com.example.AracKiralama.entity;


import com.example.AracKiralama.entity.rentacar.RentalCompany;
import com.example.AracKiralama.entity.rentacar.RentalOffice;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Entity;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee extends Person{
    private String profession;
    private double salary;


    @ManyToOne
    @JoinColumn(name = "rental_office_id", referencedColumnName = "id")
    private RentalOffice rentalOffice;
}
