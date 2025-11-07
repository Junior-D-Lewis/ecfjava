package com.ecfjava.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PorteurProjet {

    @Id
    private Long id;
    private String nom;
    private String email;
    private String password;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    

}
