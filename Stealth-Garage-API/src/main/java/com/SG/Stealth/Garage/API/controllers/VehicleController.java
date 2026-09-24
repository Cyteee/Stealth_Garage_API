package com.SG.Stealth.Garage.API.controllers;

import com.SG.Stealth.Garage.API.DTO.VehicleDTO;
import com.SG.Stealth.Garage.API.DTO.VehicleRequestDTO;
import com.SG.Stealth.Garage.API.DTO.VehicleResponseDTO;
import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.services.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import com.SG.Stealth.Garage.API.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tag(name = "Vehicle Management", description = "Endpoints for managing vehicles")
@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @Operation(summary = "Find all vehicles", description = "Find all vehicles in the database")
    @ApiResponse(responseCode = "200", description = "Found successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @GetMapping
    public ResponseEntity<Page<VehicleResponseDTO>> findAll(
            @RequestParam(name = "ano", required = false) Integer ano,
            Pageable pageable,
            @AuthenticationPrincipal User loggedUser) {

        Page<Vehicle> page = (ano != null)
                ? vehicleService.searchByYearAndOwner(ano, pageable, loggedUser)
                : vehicleService.findAllPagedByOwner(pageable, loggedUser);

        return ResponseEntity.ok(page.map(VehicleResponseDTO::new));
    }

    @Operation(summary = "Find a vehicle by ID", description = "Find a vehicle by ID in the database")
    @ApiResponse(responseCode = "200", description = "Found successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleResponseDTO> findById(
            @PathVariable Long id,
            @AuthenticationPrincipal User loggedUser) {
        Vehicle obj = vehicleService.findByIdAndOwner(id, loggedUser);
        return ResponseEntity.ok(new VehicleResponseDTO(obj));
    }

    @Operation(summary = "Create a new vehicle", description = "Create a vehicle in the database")
    @ApiResponse(responseCode = "201", description = "Vehicle created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    @PostMapping
    public ResponseEntity<Void> insert(
            @Valid @RequestBody VehicleRequestDTO dto,
            @AuthenticationPrincipal User loggedUser) {
        Vehicle obj = new Vehicle(null, dto.brandAndName(), dto.year(), dto.licensePlate(), loggedUser);
        obj = vehicleService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Update a vehicle", description = "Update a vehicle in the database")
    @ApiResponse(responseCode = "204", description = "Updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody VehicleRequestDTO dto,
            @AuthenticationPrincipal User loggedUser) {
        Vehicle obj = new Vehicle(id, dto.brandAndName(), dto.year(), dto.licensePlate(), loggedUser);
        vehicleService.update(id, obj, loggedUser);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a vehicle", description = "Delete a vehicle in the database")
    @ApiResponse(responseCode = "204", description = "Deleted successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal User loggedUser) {
        vehicleService.delete(id, loggedUser);
        logger.info("Vehicle " + id + " was deleted by user " + loggedUser.getId());
        return ResponseEntity.noContent().build();
    }
}