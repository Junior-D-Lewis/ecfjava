package com.ecfjava.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Developper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String email;
    private String password;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String technosMaitrisees;
    private Integer anciennete;
    
    @OneToMany(mappedBy = "developper")
    private List<ProjectApplication> applications;

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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTechnosMaitrisees() {
        return technosMaitrisees;
    }
    public void setTechnosMaitrisees(String technosMaitrisees) {
        this.technosMaitrisees = technosMaitrisees;
    }
    public Integer getAnciennete() {
        return anciennete;
    }
    public void setAnciennete(Integer anciennete) {
        this.anciennete = anciennete;
    }

    public List<ProjectApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<ProjectApplication> applications) {
        this.applications = applications;
    }
}
