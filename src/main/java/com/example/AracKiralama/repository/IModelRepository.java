package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.rentacar.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModelRepository extends JpaRepository<Model,Long> {
    boolean existsByModelName(String modelName);

}
