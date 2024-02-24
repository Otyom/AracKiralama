package com.example.AracKiralama.entity.rentacar;

import com.example.AracKiralama.entity.Address.Street;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RentalOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String officeName;
    private String officePhoneNumber;


    @ManyToOne
    @JoinColumn(name = "company_id")
    private RentalCompany rentalCompany;

    @ManyToOne
    @JoinColumn(name = "streetId")
    private Street street;


}
