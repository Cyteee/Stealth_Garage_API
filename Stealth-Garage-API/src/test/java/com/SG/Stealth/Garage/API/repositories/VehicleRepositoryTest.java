package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Test
    void deveInserirUmVeiculoComSucesso() {
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
