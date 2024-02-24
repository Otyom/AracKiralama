package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Customer> findOptionalByEmail(String email);

}
