package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.rentacar.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarRepository extends JpaRepository<Car,Long> {
    List<Car> findByRentalOfficeId(Long RentalOfficeId);

}
