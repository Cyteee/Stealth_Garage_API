package com.SG.Stealth.Garage.API.DTO;

import com.SG.Stealth.Garage.API.entities.Vehicle;

import java.io.Serializable;

public class VehicleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String brandAndName;
    private int year;
    private String licensePlate;

    private UserDTO owner;

    public VehicleDTO() {
    }

    public VehicleDTO(Long id, String brandAndName, int year, String licensePlate, UserDTO owner) {
        this.id = id;
        this.brandAndName = brandAndName;
        this.year = year;
        this.licensePlate = licensePlate;
        this.owner = owner;
    }

    public VehicleDTO(Vehicle obj){
        id = obj.getId();
        brandAndName = obj.getBrandAndName();
        year = obj.getYear();
        licensePlate = obj.getLicensePlate();
        owner = obj.getOwner();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }
}
