package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.controllers.exceptions.ResourceNotFoundException;
import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    public Vehicle findById(Long id) {
        Optional<Vehicle> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Vehicle insert(Vehicle obj){
        return repository.save(obj);
    }
}
