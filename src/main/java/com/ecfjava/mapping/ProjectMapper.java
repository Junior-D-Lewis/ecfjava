package com.ecfjava.mapping;

import com.ecfjava.dto.ProjectDTO;
import com.ecfjava.entities.Project;
import com.ecfjava.entities.ProjectOwner;

public class ProjectMapper {

   public static Project toEntity(ProjectDTO dto) {
    if (dto == null) return null;

    Project entity = new Project();

    entity.setTitre(dto.getTitre());
    entity.setDescription(dto.getDescription());
    entity.setBudget(dto.getBudget());
    entity.setStatut(dto.getStatut());
    entity.setThematique(dto.getThematique());
    entity.setDateLivraison(dto.getDateLivraison());

    if (dto.getProjectOwnerId() != null) {
        ProjectOwner owner = new ProjectOwner();
        owner.setId(dto.getProjectOwnerId());
        entity.setProjectOwner(owner);
    }

    return entity;
}


   public static ProjectDTO toDTO(Project project) {

    if (project == null) return null;

    ProjectDTO dto = new ProjectDTO();

    dto.setId(project.getId());
    dto.setTitre(project.getTitre());
    dto.setDescription(project.getDescription());
    dto.setBudget(project.getBudget());
    dto.setStatut(project.getStatut());
    dto.setThematique(project.getThematique());
    dto.setDateLivraison(project.getDateLivraison());

    if (project.getProjectOwner() != null) {
        dto.setProjectOwnerId(project.getProjectOwner().getId());
    }

    return dto;
}


}
