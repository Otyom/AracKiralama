package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.rentacar.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment,Long> {
}
