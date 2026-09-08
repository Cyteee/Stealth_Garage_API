package com.SG.Stealth.Garage.API.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_service_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_id", nullable = false)
    private Technician technician;
}