package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.rentacar.RentalCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentalCompanyRepository extends JpaRepository<RentalCompany,Long> {

    boolean existsByCompanyName(String companyName);

}
