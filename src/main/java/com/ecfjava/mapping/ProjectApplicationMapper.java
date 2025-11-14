package com.ecfjava.mapping;

import com.ecfjava.dto.ProjectApplicationDTO;
import com.ecfjava.entities.ProjectApplication;

public class ProjectApplicationMapper {

    public static ProjectApplicationDTO toDTO(ProjectApplication application) {
        
        if (application == null) return null;

        ProjectApplicationDTO dto = new ProjectApplicationDTO();

        dto.setId(application.getId());
        dto.setDateCandidature(application.getDateCandidature());
        dto.setStatus(application.getStatus());
        dto.setProjectId(application.getProjet().getId());
        dto.setDevelopperId(application.getDevelopper().getId());
        

        return dto;
    }
}
