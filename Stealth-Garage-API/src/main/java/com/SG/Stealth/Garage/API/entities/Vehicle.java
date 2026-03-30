package com.SG.Stealth.Garage.API.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brandAndName;
    @Column(name = "vehicle_year")
    private int year;
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Vehicle() {
    }

    public Vehicle(Long id, String brand, int year, String placa, User owner) {
        this.id = id;
        this.brandAndName = brand;
        this.year = year;
        this.licensePlate = placa;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getBrandAndName() {
        return brandAndName;
    }

    public void setBrandAndName(String brandAndName) {
        this.brandAndName = brandAndName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
