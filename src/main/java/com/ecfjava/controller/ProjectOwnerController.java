package com.ecfjava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecfjava.dto.AddProjectOwnerDTO;
import com.ecfjava.dto.ProjectDTO;
import com.ecfjava.dto.ProjectOwnerDTO;
import com.ecfjava.entities.Project;
import com.ecfjava.entities.ProjectOwner;
import com.ecfjava.mapping.ProjectOwnerMapper;
import com.ecfjava.repositories.ProjectOwnerRepository;

@RestController
@RequestMapping("api/ProjectOwner")
public class ProjectOwnerController {

    private final ProjectOwnerRepository ProjectOwnerRepository;

    public ProjectOwnerController(ProjectOwnerRepository ProjectOwnerRepository) {
        this.ProjectOwnerRepository = ProjectOwnerRepository;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ProjectOwnerDTO> signUpProjectOwner(@RequestBody AddProjectOwnerDTO ProjectOwnerDto) {
        ProjectOwner savedProjectOwner = ProjectOwnerRepository.saveAndFlush(ProjectOwnerMapper.toEntity(ProjectOwnerDto));
        return ResponseEntity.ok(ProjectOwnerMapper.toDTO(savedProjectOwner));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ProjectOwnerDTO> signInProjectOwner(@RequestBody AddProjectOwnerDTO ProjectOwnerDto) {
        ProjectOwner projectOwner = ProjectOwnerRepository.findByEmailAndPassword(
                ProjectOwnerDto.getEmail(),
                ProjectOwnerDto.getPassword()
        ).orElseThrow(() -> new RuntimeException("Invalid credentials"));

        return ResponseEntity.ok(ProjectOwnerMapper.toDTO(projectOwner));
    }

}