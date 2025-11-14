package com.ecfjava.mapping;

import com.ecfjava.dto.AddProjectOwnerDTO;
import com.ecfjava.dto.ProjectOwnerDTO;
import com.ecfjava.entities.ProjectOwner;

public class ProjectOwnerMapper {

    public static ProjectOwnerDTO toDTO(ProjectOwner ProjectOwner) {

        if (ProjectOwner == null) return null;

        ProjectOwnerDTO dto = new ProjectOwnerDTO();

        dto.setId(ProjectOwner.getId());
        dto.setNom(ProjectOwner.getNom());
        dto.setEmail(ProjectOwner.getEmail());
        return dto;
    }

    public static ProjectOwner toEntity(AddProjectOwnerDTO dto) {

        if (dto == null) return null;

        ProjectOwner ProjectOwner = new ProjectOwner();

        ProjectOwner.setNom(dto.getNom());
        ProjectOwner.setEmail(dto.getEmail());
        ProjectOwner.setPassword(dto.getPassword());
        return ProjectOwner;
    }
}
