package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.controllers.exceptions.ResourceNotFoundException;
import com.SG.Stealth.Garage.API.repositories.VehicleRepository;
import com.SG.Stealth.Garage.API.repositories.VehicleRepositoryTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void deveLancarExcecaoAoDeletarVeiculoNaoEncontrado(){
        Long idInexistente = 2L;

        Mockito.doThrow(EmptyResultDataAccessException.class).when(vehicleRepository).deleteById(idInexistente);

        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            vehicleService.delete(idInexistente);
        }, "Deveria lancar uma excecao informando que o veiculo nao foi deletado");
    }
}
