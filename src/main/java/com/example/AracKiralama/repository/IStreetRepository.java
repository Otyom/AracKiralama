package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.Address.Neighborhood;
import com.example.AracKiralama.entity.Address.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStreetRepository extends JpaRepository<Street,Long> {
    boolean existsByNeighborhoodAndStreetName(Neighborhood neighborhood, String streetName);
}
