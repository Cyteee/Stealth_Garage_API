package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
