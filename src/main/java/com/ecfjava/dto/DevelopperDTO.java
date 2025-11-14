package com.ecfjava.dto;

public class DevelopperDTO {

    private Long id;
    private String nom;
    private String email;
    private String description;
    private String technosMaitrisees;
    private Integer anciennete;

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

    // 
}
