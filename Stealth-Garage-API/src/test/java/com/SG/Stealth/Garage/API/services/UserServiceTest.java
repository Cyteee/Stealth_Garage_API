package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.entities.User;
import com.SG.Stealth.Garage.API.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder, List.of("admin@teste.com"));
    }

    @Test
    void deveAtribuirRoleAdminQuandoEmailEstiverNaLista() {
        User user = new User();
        user.setEmail("admin@teste.com");
        user.setPassword("senha123");

        Mockito.when(passwordEncoder.encode("senha123")).thenReturn("senhaCodificada");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User result = userService.insert(user);

        assertNotNull(result);
        assertEquals("senhaCodificada", result.getPassword());

        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("senha123");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void deveAtribuirRoleUserQuandoEmailNaoEstiverNaLista() {
        User user = new User();
        user.setEmail("cliente@teste.com");
        user.setPassword("senha123");

        Mockito.when(passwordEncoder.encode("senha123")).thenReturn("senhaCodificada");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User result = userService.insert(user);

        assertNotNull(result);

        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("senha123");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}