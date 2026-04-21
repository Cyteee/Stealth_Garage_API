package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.entities.User;
import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.repositories.UserRepository;
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
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void deveAtualizarUmVeiculo(){
        Long idDoUsuario = 1L;

        User usuarioVelho = new User();
        usuarioVelho.setId(idDoUsuario);
        usuarioVelho.setName("Ozias Souza");
        usuarioVelho.setPhoneNumber("11995862658");

        User dadosNovosDaRequisicao = new User();
        dadosNovosDaRequisicao.setName("Osias Souza");
        dadosNovosDaRequisicao.setPhoneNumber("11985684983");

        Mockito.when(userRepository.getReferenceById(idDoUsuario)).thenReturn(usuarioVelho);

        Mockito.when(userRepository.save(usuarioVelho)).thenReturn(usuarioVelho);

        User resultado = userService.update(idDoUsuario, dadosNovosDaRequisicao);

        assertEquals(1L, resultado.getId());
        assertEquals("Osias Souza", resultado.getName());
        assertEquals("11985684983", resultado.getPhoneNumber());
    }
}
