package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE v.year = :ano")
    List<Vehicle> findByYearCustom(@Param("ano") Integer ano);
}
