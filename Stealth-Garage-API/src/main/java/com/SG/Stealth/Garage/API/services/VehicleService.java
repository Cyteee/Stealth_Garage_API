package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.DTO.VehicleDTO;
import com.SG.Stealth.Garage.API.controllers.exceptions.DatabaseException;
import com.SG.Stealth.Garage.API.controllers.exceptions.ResourceNotFoundException;
import com.SG.Stealth.Garage.API.entities.User;
import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Page<Vehicle> findAllPagedByOwner(Pageable pageable, User owner) {
        return vehicleRepository.findAllByOwner(owner, pageable);
    }

    public Page<Vehicle> searchByYearAndOwner(Integer year, Pageable pageable, User owner) {
        return vehicleRepository.findByYearAndOwner(year, owner, pageable);
    }

    public Vehicle findByIdAndOwner(Long id, User owner) {
        return vehicleRepository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Vehicle insert(Vehicle obj) {
        return vehicleRepository.save(obj);
    }

    public Vehicle update(Long id, Vehicle obj, User loggedUser) {
        Vehicle entity = findByIdAndOwner(id, loggedUser);
        entity.setBrandAndName(obj.getBrandAndName());
        entity.setYear(obj.getYear());
        entity.setLicensePlate(obj.getLicensePlate());
        return vehicleRepository.save(entity);
    }

    public void delete(Long id, User loggedUser) {
        Vehicle entity = findByIdAndOwner(id, loggedUser);
        try {
            vehicleRepository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Cannot delete vehicle because it has related service orders.");
        }
    }
}