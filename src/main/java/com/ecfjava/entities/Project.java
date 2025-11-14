package com.ecfjava.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.ecfjava.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDateTime dateLivraison;
    private Float budget;
    private String thematique;

    @Enumerated(EnumType.STRING)
    private Status statut;

    @ManyToOne
    @JoinColumn(name = "project_owner_id")
    private ProjectOwner projectOwner;

    @OneToMany(mappedBy = "project")
    private List <ProjectApplication> applications;

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

    public ProjectOwner getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(ProjectOwner projectOwner) {
        this.projectOwner = projectOwner;
    }

    public List<ProjectApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<ProjectApplication> applications) {
        this.applications = applications;
    }


}
