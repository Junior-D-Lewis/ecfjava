package com.ecfjava.dto;

import java.time.LocalDateTime;

import com.ecfjava.enums.Status;

public class ProjectDTO {

    private Long id;
    private String titre;
    private String description;
    private LocalDateTime dateLivraison;
    private Float budget;
    private String thematique;
    private Status statut;

    private Long projectOwnerId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }
    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }
    public Float getBudget() {
        return budget;
    }
    public void setBudget(Float budget) {
        this.budget = budget;
    }
    public String getThematique() {
        return thematique;
    }
    public void setThematique(String thematique) {
        this.thematique = thematique;
    }
    public Status getStatut() {
        return statut;
    }
    public void setStatut(Status statut) {
        this.statut = statut;
    }
    public Long getProjectOwnerId() {
        return projectOwnerId;
    }
    public void setProjectOwnerId(Long projectOwnerId) {
        this.projectOwnerId = projectOwnerId;
    }
}
