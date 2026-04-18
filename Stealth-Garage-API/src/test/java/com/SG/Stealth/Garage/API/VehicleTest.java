package com.SG.Stealth.Garage.API;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VehicleTest {

    @Test
    void calcularIdadeDoVeiculo(){
        int anoAtual = 2026;
        int anoFabricacaoCobalt = 2012;
        int idadeEsperada = 14;

        int idadeCalculada = anoAtual - anoFabricacaoCobalt;

        assertEquals(idadeEsperada, idadeCalculada, "A idade do veiculo deveria ser 14 anos: ");
    }
}
