package com.SG.Stealth.Garage.API.controllers;

import com.SG.Stealth.Garage.API.config.security.TokenService;
import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.services.UserService;
import com.SG.Stealth.Garage.API.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VehicleService vehicleService;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private UserService userService;

    @Test
    void deveRetornarVeiculoPeloIdcomStatus200() throws Exception{
        Long idBuscado = 1L;
        Vehicle veiculoFalso = new Vehicle();
        veiculoFalso.setId(idBuscado);
        veiculoFalso.setBrandAndName("Fiat Marea");

        Mockito.when(vehicleService.findById(idBuscado)).thenReturn(veiculoFalso);

        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle/{id}", idBuscado)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idBuscado))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandAndName").value("Fiat Marea"));
    }
}
