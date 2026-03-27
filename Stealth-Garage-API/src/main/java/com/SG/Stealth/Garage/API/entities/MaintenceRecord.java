package com.SG.Stealth.Garage.API.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_maintence_record")
public class MaintenceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant date;
    private String description;
    private double km;

    @ManyToMany
    @JoinTable(name = "tb_maintence_record_part)", joinColumns = @JoinColumn(name = "maintence_record_id"), inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Part> parts = new HashSet<>();

    public MaintenceRecord() {
    }

    public MaintenceRecord(Long Id, Instant date, String description, double km) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.km = km;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public Long getId() {
        return id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getTotal(){
        double soma = 0.0;
        for (Part p : parts){
            soma += p.getUsedPartsPrice();
        } return soma;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MaintenceRecord that = (MaintenceRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
