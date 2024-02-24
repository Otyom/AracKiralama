package com.example.AracKiralama.repository;


import com.example.AracKiralama.entity.rentacar.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMarkRepository extends JpaRepository<Mark,Long> {
    boolean existsByMarkName(String markName);

}
