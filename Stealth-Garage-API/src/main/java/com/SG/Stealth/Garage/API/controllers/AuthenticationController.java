package com.SG.Stealth.Garage.API.controllers;

import com.SG.Stealth.Garage.API.DTO.AuthenticationDTO;
import com.SG.Stealth.Garage.API.DTO.LoginResponseDTO;
import com.SG.Stealth.Garage.API.DTO.UserDTO;
import com.SG.Stealth.Garage.API.config.security.TokenService;
import com.SG.Stealth.Garage.API.entities.User;
import com.SG.Stealth.Garage.API.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserDTO objDto) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(objDto.getPassword());
        User obj = userService.fromDTO(objDto);
        obj.setPassword(encryptedPassword);
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
