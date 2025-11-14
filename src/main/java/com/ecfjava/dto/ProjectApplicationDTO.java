package com.ecfjava.dto;

import java.time.LocalDateTime;

import com.ecfjava.enums.Status;

public class ProjectApplicationDTO {
    private Long id;
    private Long projectId;
    private Long developperId;
    private LocalDateTime dateCandidature;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getDevelopperId() {
        return developperId;
    }

    public void setDevelopperId(Long developperId) {
        this.developperId = developperId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDateCandidature() {
        return dateCandidature;
    }

    public void setDateCandidature(LocalDateTime dateCandidature) {
        this.dateCandidature = dateCandidature;
    }
}
