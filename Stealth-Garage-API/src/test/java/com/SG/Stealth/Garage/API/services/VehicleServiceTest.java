package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void deveInserirUmVeiculo(){
        Vehicle veiculoParaSalvar = new Vehicle();
        veiculoParaSalvar.setBrandAndName("Toyota Hilux");
        veiculoParaSalvar.setYear(1996);

        Vehicle veiculoRetornoDoBanco = new Vehicle();
        veiculoRetornoDoBanco.setId(14L);
        veiculoRetornoDoBanco.setBrandAndName("Toyota Hilux");
        veiculoRetornoDoBanco.setYear(1996);

        Mockito.when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(veiculoRetornoDoBanco);

        Vehicle resultado = vehicleService.insert(veiculoParaSalvar);

        assertNotNull(resultado.getId(), "O ID não deveria ser nulo, provando que passou pelo banco falso");
        assertEquals(14L, resultado.getId());
        assertEquals("Toyota Hilux", resultado.getBrandAndName());
    }
}
