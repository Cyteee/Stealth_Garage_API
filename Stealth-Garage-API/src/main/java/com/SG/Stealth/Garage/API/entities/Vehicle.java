package com.SG.Stealth.Garage.API.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private int year;
    private double licensePlate;

    @OneToMany(mappedBy = "client")
    private List<Vehicle> vehicles = new ArrayList<>();

    public Vehicle() {
    }

    public Vehicle(Long id, String brand, int year, double placa) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.licensePlate = placa;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(double licensePlate) {
        this.licensePlate = licensePlate;
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
