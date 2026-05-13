package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE v.year = :ano")
    List<Vehicle> findByYearCustom(@Param("ano") Integer ano);

    @Query("SELECT v FROM Vehicle v WHERE v.brandAndName LIKE %:keyword%")
    List<Vehicle> searchByBrandOrNameJPQL(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM tb_vehicle WHERE vehicle_year >= :year", nativeQuery = true)
    List<Vehicle> findVehiclesNewerThanNative(@Param("year") Integer year);

    @Query("SELECT v FROM Vehicle v WHERE v.year = :ano")
    Page<Vehicle> searchByYear(@Param("ano") Integer ano, Pageable pageable);
}
