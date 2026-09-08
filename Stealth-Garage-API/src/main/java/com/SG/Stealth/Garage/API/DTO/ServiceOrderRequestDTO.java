package com.SG.Stealth.Garage.API.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServiceOrderRequestDTO(

        @NotNull(message = "O ID do técnico é obrigatório")
        Long technicianId,

        @NotBlank(message = "A descrição do serviço não pode estar vazia")
        String serviceDescription
) {
}