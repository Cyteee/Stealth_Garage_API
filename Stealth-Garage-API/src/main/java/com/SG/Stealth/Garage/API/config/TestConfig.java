package com.SG.Stealth.Garage.API.config;

import com.SG.Stealth.Garage.API.entities.MaintenceRecord;
import com.SG.Stealth.Garage.API.entities.Part;
import com.SG.Stealth.Garage.API.entities.User;
import com.SG.Stealth.Garage.API.entities.Vehicle;
import com.SG.Stealth.Garage.API.repositories.MaintenceRecordRepository;
import com.SG.Stealth.Garage.API.repositories.PartRepository;
import com.SG.Stealth.Garage.API.repositories.UserRepository;
import com.SG.Stealth.Garage.API.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private MaintenceRecordRepository maintenceRecordRepository;

    @Override
    public void run(String... args) throws Exception{

        User u1 = new User(null, "Marcelo", "marceloodev@gmail.com", "42942345305");

        userRepository.saveAll(Arrays.asList(u1));

        Vehicle v1 = new Vehicle(null, "Chevrolet Cobalt", 2012, "woe4d2043");

        vehicleRepository.saveAll(Arrays.asList(v1));

        Part p1 = new Part(null, "Estofado do banco dianteiro motorista", 530.00);

        partRepository.saveAll(Arrays.asList(p1));

        MaintenceRecord mR1 = new MaintenceRecord(null, Instant.parse("2026-03-27T10:00:00Z"), "Troca do estofado do banco dianteiro motorista", 700.00);

        mR1.getParts().add(p1);

        maintenceRecordRepository.saveAll(Arrays.asList(mR1));
    }
}
