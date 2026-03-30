package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.controllers.exceptions.DatabaseException;
import com.SG.Stealth.Garage.API.controllers.exceptions.ResourceNotFoundException;
import com.SG.Stealth.Garage.API.entities.User;
import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Vehicle update(Long id, Vehicle obj){
        try {
            Vehicle entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Vehicle entity, Vehicle obj){
        entity.setBrand(obj.getBrand());
        entity.setYear(obj.getYear());
        entity.setLicensePlate(obj.getLicensePlate());
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
