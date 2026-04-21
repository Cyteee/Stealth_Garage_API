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
    void deveAtualizarUmVeiculo(){
        Long idDoCarro = 1L;

        Vehicle veiculoAntigoNoBanco = new Vehicle();
        veiculoAntigoNoBanco.setId(idDoCarro);
        veiculoAntigoNoBanco.setBrandAndName("Corsa Wind");
        veiculoAntigoNoBanco.setYear(1996);

        Vehicle dadosNovosDaRequisicao = new Vehicle();
        dadosNovosDaRequisicao.setBrandAndName("Corsa Wind Rebaixado");
        dadosNovosDaRequisicao.setYear(1998);

        Mockito.when(vehicleRepository.getReferenceById(idDoCarro)).thenReturn(veiculoAntigoNoBanco);

        Mockito.when(vehicleRepository.save(veiculoAntigoNoBanco)).thenReturn(veiculoAntigoNoBanco);

        Vehicle resultado = vehicleService.update(idDoCarro, dadosNovosDaRequisicao);

        assertEquals(1L, resultado.getId());
        assertEquals("Corsa Wind Rebaixado", resultado.getBrandAndName());
        assertEquals(1998, resultado.getYear());
    }
}
