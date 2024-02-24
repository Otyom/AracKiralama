package com.example.AracKiralama.repository;

import com.example.AracKiralama.dto.request.SaveCarClassRequestDto;
import com.example.AracKiralama.entity.rentacar.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarClassRepositorty extends JpaRepository<CarClass,Long> {
    boolean existsByClasName(String clasName);
}
