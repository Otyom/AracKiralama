package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByCarId(Long carId);
}
