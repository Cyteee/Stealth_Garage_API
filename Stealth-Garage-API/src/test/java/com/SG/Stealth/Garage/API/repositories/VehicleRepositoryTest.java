package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void deveSalvarUmVeiculoNoBancoH2(){
        Vehicle novoVeiculo = new Vehicle();
        novoVeiculo.setBrandAndName("Volkswagen Tera");

        Vehicle veiculoSalvo = vehicleRepository.save(novoVeiculo);

        assertNotNull(veiculoSalvo.getId(), "O ID nao deverai ser nulo, o banco deveria ter gerado um!");
        assertEquals("Volkswagen Tera", veiculoSalvo.getBrandAndName());
    }
}
