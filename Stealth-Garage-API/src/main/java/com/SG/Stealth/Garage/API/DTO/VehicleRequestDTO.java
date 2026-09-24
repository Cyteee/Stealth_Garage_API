package com.SG.Stealth.Garage.API.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleRequestDTO(
        @NotBlank String brandAndName,
        @NotNull Integer year,
        @NotBlank String licensePlate
) {}