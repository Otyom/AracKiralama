package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.Address.District;
import com.example.AracKiralama.entity.Address.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INeighborhoodRepository extends JpaRepository<Neighborhood,Long> {



    boolean existsByDistrictAndNeighborhoodName(District district, String streetName);
}
