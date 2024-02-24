package com.example.AracKiralama.repository;



import com.example.AracKiralama.entity.Address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
    Boolean existsByCityName(String cityName);
}
