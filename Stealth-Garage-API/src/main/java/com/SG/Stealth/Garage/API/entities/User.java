package com.SG.Stealth.Garage.API.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name =  "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "client_id")
    private User name;
    private String email;
    private String phoneNumber;

    public User() {
    }

    public User(Long id, User name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public User getName() {
        return name;
    }

    public void setName(User name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
