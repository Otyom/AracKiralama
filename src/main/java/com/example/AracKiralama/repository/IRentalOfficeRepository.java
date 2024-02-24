package com.example.AracKiralama.repository;

import com.example.AracKiralama.entity.rentacar.RentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IRentalOfficeRepository extends JpaRepository<RentalOffice,Long> {
    @Query("SELECT r FROM RentalOffice r " +
            "JOIN FETCH r.street s " +
            "JOIN FETCH s.neighborhood n " +
            "JOIN FETCH n.district d " +
            "JOIN FETCH d.city c " +
            "WHERE r.id = :rentalOfficeId")
    Optional<RentalOffice>findRentalOfficeWithDetailsById(@Param("rentalOfficeId") Long rentalOfficeId);

}
