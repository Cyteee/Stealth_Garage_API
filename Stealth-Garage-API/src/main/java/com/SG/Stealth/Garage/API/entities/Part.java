package com.SG.Stealth.Garage.API.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usedPartsName;
    private double usedPartsPrice;

    @JsonIgnore
    @ManyToMany(mappedBy = "parts")
    private Set<MaintenceRecord> records = new HashSet<>();

    public Part() {
    }

    public Part(Long id, String usedPartsName, double usedPartsPrice) {
        this.id = id;
        this.usedPartsName = usedPartsName;
        this.usedPartsPrice = usedPartsPrice;
    }

    public Set<MaintenceRecord> getRecords() {
        return records;
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
