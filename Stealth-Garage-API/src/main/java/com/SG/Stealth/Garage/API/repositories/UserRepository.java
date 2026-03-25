package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
