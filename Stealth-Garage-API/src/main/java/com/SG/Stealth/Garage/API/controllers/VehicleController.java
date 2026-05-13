package com.SG.Stealth.Garage.API.controllers;

import com.SG.Stealth.Garage.API.DTO.VehicleDTO;
import com.SG.Stealth.Garage.API.controllers.exceptions.ResourceNotFoundException;
import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.services.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import com.SG.Stealth.Garage.API.entities.User;

import org.springframework.data.domain.Pageable;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Vehicle Management", description = "Endpoints for managing vehicles")
@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Operation(summary = "Find all vehicles", description = "Find all vehicles in the database")
    @ApiResponse(responseCode = "200", description = "Found successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @GetMapping
    public ResponseEntity<Page<VehicleDTO>> findAll(@RequestParam(name = "ano", required = false) Integer ano, Pageable pageable) {
        Page<Vehicle> page;
        if (ano != null) {
            page = vehicleService.searchByYear(ano, pageable);
        } else {
            page = vehicleService.findAllPaged(pageable);
        }
        Page<VehicleDTO> pageDto = page.map(VehicleDTO::new);
        return ResponseEntity.ok().body(pageDto);
    }

    @Operation(summary = "Find a vehicle by ID", description = "Find a vehicle by ID in the database")
    @ApiResponse(responseCode = "200", description = "Found successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id){
        Vehicle obj = vehicleService.findById(id);
        return ResponseEntity.ok().body(new VehicleDTO(obj));
    }

    @Operation(summary = "Create a new vehicle", description = "Create a vehicle in the database")
    @ApiResponse(responseCode = "201", description = "Vehicle created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody VehicleDTO objDto){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();
        Vehicle obj = vehicleService.fromDTO(objDto, loggedUser);
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
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody VehicleDTO objDto){
        Vehicle obj = vehicleService.fromDTO(objDto, null);
        obj.setId(id);
        obj = vehicleService.update(id, obj);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a vehicle", description = "Delete a vehicle in the database")
    @ApiResponse(responseCode = "204", description = "Deleted successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}