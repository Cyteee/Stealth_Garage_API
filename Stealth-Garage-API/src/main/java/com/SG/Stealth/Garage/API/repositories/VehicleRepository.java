package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
