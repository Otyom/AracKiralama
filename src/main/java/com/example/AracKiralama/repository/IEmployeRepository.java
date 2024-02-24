package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Employee> findOptionalByEmail(String email);

}
