package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.Address.City;
import com.example.AracKiralama.entity.Address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistrictRepository extends JpaRepository<District,Long> {
    boolean existsByCityAndDistrictName(City city, String districtName);

}
