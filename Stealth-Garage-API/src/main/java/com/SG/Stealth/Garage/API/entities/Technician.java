package com.SG.Stealth.Garage.API.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_technician")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialty;
    private String sector;
}