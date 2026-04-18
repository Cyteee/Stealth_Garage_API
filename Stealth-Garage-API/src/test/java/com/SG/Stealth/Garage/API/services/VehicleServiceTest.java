package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void encotrarVeiculoPorId(){
        Vehicle veiculoFalso = new Vehicle();
        veiculoFalso.setId(1L);
        veiculoFalso.setBrandAndName("Toyota Corolla");

        Mockito.when(vehicleRepository.findById(1L)).thenReturn(Optional.of(veiculoFalso));

        Vehicle veiculoEncontrado = vehicleService.findById(1L);

        assertNotNull(veiculoEncontrado, "O veiculo nao deveria ser nulo");
        assertEquals("Toyota Corolla", veiculoEncontrado.getBrandAndName());
        assertEquals(1L, veiculoEncontrado.getId());
    }
}
