package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.DTO.VehicleDTO;
import com.SG.Stealth.Garage.API.controllers.exceptions.DatabaseException;
import com.SG.Stealth.Garage.API.controllers.exceptions.ResourceNotFoundException;
import com.SG.Stealth.Garage.API.entities.User;
import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.repositories.UserRepository;
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
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findById(Long id) {
        Optional<Vehicle> obj = vehicleRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Vehicle insert(Vehicle obj){
        return vehicleRepository.save(obj);
    }

    public Vehicle update(Long id, Vehicle obj){
        try {
            Vehicle entity = vehicleRepository.getReferenceById(id);
            updateData(entity, obj);
            return vehicleRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Vehicle entity, Vehicle obj){
        entity.setBrandAndName(obj.getBrandAndName());
        entity.setYear(obj.getYear());
        entity.setLicensePlate(obj.getLicensePlate());
    }

    public void delete(Long id){
        try {
            vehicleRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Vehicle fromDTO(VehicleDTO objDto) {

        User owner = userRepository.findById(objDto.getOwner().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        return new Vehicle(
                objDto.getId(),
                objDto.getBrandAndName(),
                objDto.getYear(),
                objDto.getLicensePlate(),
                owner
        );
    }
}