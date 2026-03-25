package com.SG.Stealth.Garage.API.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usedPartsName;
    private double usedPartsPrice;

    public Part() {
    }

    public Part(Long id, String usedPartsName, double usedPartsPrice) {
        this.id = id;
        this.usedPartsName = usedPartsName;
        this.usedPartsPrice = usedPartsPrice;
    }

    public Long getId() {
        return id;
    }

    public String getUsedPartsName() {
        return usedPartsName;
    }

    public void setUsedPartsName(String usedPartsName) {
        this.usedPartsName = usedPartsName;
    }

    public double getUsedPartsPrice() {
        return usedPartsPrice;
    }

    public void setUsedPartsPrice(double usedPartsPrice) {
        this.usedPartsPrice = usedPartsPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(id, part.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
