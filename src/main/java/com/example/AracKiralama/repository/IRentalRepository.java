package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.rentacar.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRentalRepository extends JpaRepository<Rental,Long> {
    Optional<Rental> findByCustomerId(Long rentalId);



}
