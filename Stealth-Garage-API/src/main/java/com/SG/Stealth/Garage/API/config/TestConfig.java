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
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception{

        User u1 = new User(null, "Marcelo", "marceloodev@gmail.com", "219240349503", passwordEncoder.encode("123456"));
        User u2 = new User(null, "Jose", "jose1923@hotmail.com", "14924030535", passwordEncoder.encode("123456"));
        User u3 = new User(null, "Isabella", "isaeebbela@outlook.com", "34924207483", passwordEncoder.encode("123456"));
        User u4 = new User(null, "Joao", "joaopamonha@gmail.com", "11930233024", passwordEncoder.encode("123456"));
        User u5 = new User(null, "Jose", "josepamonha@gmail.com", "11943049674", passwordEncoder.encode("123456"));

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));

        Vehicle v1 = new Vehicle(null, "Chevrolet Cobalt", 2012, "woe4d2043", u1);
        Vehicle v2 = new Vehicle(null, "Toyota Corolla", 2026, "woe4g20", u1);
        Vehicle v3 = new Vehicle(null, "Volkswagen Polo", 2008, "rwe4o05", u2);
        Vehicle v4 = new Vehicle(null, "Honda Civic", 2010, "ops5v02", u3);
        Vehicle v5 = new Vehicle(null, "Fiat Uno Mille", 2013, "dve1e03", u4);
        Vehicle v6 = new Vehicle(null, "Hyundai Creta", 2018, "hbt0g52", u5);

        vehicleRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6));

        Part p1 = new Part(null, "Estofado do banco dianteiro motorista", 530.00);
        Part p2 = new Part(null, "Macaneta das quatro portas", 698.00);
        Part p3 = new Part(null, "Duas lanternas dianteiras", 4200.00);
        Part p4 = new Part(null, "Motor completo", 9680.00);
        Part p5 = new Part(null, "Porta traseira esquerda", 790.00);


        partRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        MaintenceRecord mR1 = new MaintenceRecord(null, Instant.parse("2026-03-27T10:31:35Z"), "Troca do estofado do banco dianteiro motorista, troca das macanetas das quatro portas", 198654.00);
        MaintenceRecord mR2 = new MaintenceRecord(null, Instant.parse("2026-01-09T12:26:13Z"), "Troca de duas lanternas dianteiras", 9846.00);
        MaintenceRecord mR3 = new MaintenceRecord(null, Instant.parse("2026-03-27T13:40:53Z"), "Troca do motor completo", 214654.00);
        MaintenceRecord mR4 = new MaintenceRecord(null, Instant.parse("2026-03-27T08:46:43Z"), "Troca da porta traseira esquerda", 148654.00);

        mR1.getParts().addAll(Arrays.asList(p1, p2));
        mR2.getParts().add(p3);
        mR3.getParts().add(p4);
        mR4.getParts().add(p5);

        maintenceRecordRepository.saveAll(Arrays.asList(mR1, mR2, mR3, mR4));
    }
}
