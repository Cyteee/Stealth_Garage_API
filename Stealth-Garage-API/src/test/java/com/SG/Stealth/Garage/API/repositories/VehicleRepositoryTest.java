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
        veiculoParaSalvar.setBrandAndName("Honda Civic");
        veiculoParaSalvar.setYear(2020);

        Vehicle veiculoRetornoDoBanco = new Vehicle();
        veiculoRetornoDoBanco.setId(10L);
        veiculoRetornoDoBanco.setBrandAndName("Honda Civic");
        veiculoRetornoDoBanco.setYear(2020);

        Mockito.when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(veiculoRetornoDoBanco);

        Vehicle resultado = vehicleService.insert(veiculoParaSalvar);

        assertNotNull(resultado.getId(), "O ID não deveria ser nulo, provando que passou pelo banco falso");
        assertEquals(10L, resultado.getId());
        assertEquals("Honda Civic", resultado.getBrandAndName());
    }
}
