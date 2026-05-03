package com.SG.Stealth.Garage.API.controllers;

import com.SG.Stealth.Garage.API.DTO.AuthenticationDTO;
import com.SG.Stealth.Garage.API.DTO.LoginResponseDTO;
import com.SG.Stealth.Garage.API.DTO.UserDTO;
import com.SG.Stealth.Garage.API.config.security.TokenService;
import com.SG.Stealth.Garage.API.entities.User;
import com.SG.Stealth.Garage.API.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Auth Management", description = "Endpoints for managing auths")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Login and take a token", description = "Log and take a token to authorize yourself in the sistem")
    @ApiResponse(responseCode = "200", description = "Successfully logged in")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Register a new user", description = "Register a new user in the database")
    @ApiResponse(responseCode = "201", description = "Created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserDTO objDto) {
        User obj = userService.fromDTO(objDto);
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
