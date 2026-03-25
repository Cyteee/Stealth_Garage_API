package com.SG.Stealth.Garage.API.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_maintence_record")
public class MaintenceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String description;
    private double km;

    @ManyToMany
    @JoinTable(name = "tb_maintence_record_part)", joinColumns = @JoinColumn(name = "maintence_record_id"), inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Part> parts = new HashSet<>();

    public MaintenceRecord() {
    }

    public MaintenceRecord(Date date, String description, double km) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
