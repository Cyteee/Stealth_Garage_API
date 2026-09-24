package com.SG.Stealth.Garage.API.DTO;

import com.SG.Stealth.Garage.API.entities.Vehicle;

public record VehicleResponseDTO(Long id, String brandAndName, Integer year, String licensePlate) {
    public VehicleResponseDTO(Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getBrandAndName(), vehicle.getYear(), vehicle.getLicensePlate());
    }
}