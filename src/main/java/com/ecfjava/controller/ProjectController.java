package com.ecfjava.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecfjava.dto.ProjectApplicationDTO;
import com.ecfjava.dto.ProjectDTO;
import com.ecfjava.entities.Project;
import com.ecfjava.entities.ProjectApplication;
import com.ecfjava.entities.ProjectOwner;
import com.ecfjava.enums.Status;
import com.ecfjava.mapping.ProjectMapper;
import com.ecfjava.repositories.ProjectApplicationRepository;
import com.ecfjava.repositories.ProjectOwnerRepository;
import com.ecfjava.repositories.ProjectRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/project")
public class ProjectController {

    private final ProjectOwnerRepository projectOwnerRepository;
    private final ProjectRepository projectRepository;
    private final ProjectApplicationRepository projectApplicationRepository;

    public ProjectController(ProjectOwnerRepository projectOwnerRepository, ProjectRepository projectRepository, ProjectApplicationRepository projectApplicationRepository) {
        this.projectOwnerRepository = projectOwnerRepository;
        this.projectRepository = projectRepository;
        this.projectApplicationRepository = projectApplicationRepository;
    }

    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOs = projects.stream()
                .map(ProjectMapper::toDTO)
                .toList();
        return ResponseEntity.ok(projectDTOs);
    }

    @PostMapping()
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {

        ProjectOwner projectOwner = projectOwnerRepository.findById(projectDTO.getProjectOwnerId().longValue())
                .orElseThrow(() -> new RuntimeException("Project Owner not found"));
        Project project = ProjectMapper.toEntity(projectDTO);
        project.setProjectOwner(projectOwner);
        return ResponseEntity.ok(ProjectMapper.toDTO(projectRepository.saveAndFlush(project)));

    }

    @GetMapping("/{projectId}/applications")
    public ResponseEntity<List<ProjectApplication>> getApplicationsByProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<ProjectApplication> applications = project.getApplications();

        return ResponseEntity.ok(applications);
    }

    @PutMapping("/applications/{id}/accept")
    public ProjectApplication acceptApplication(@PathVariable Long id) {
        ProjectApplication application = projectApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(Status.ACCEPTE);
        return projectApplicationRepository.saveAndFlush(application);
    }

    @PutMapping("/applications/{id}/reject")
    public ProjectApplication rejectApplication(@PathVariable Long id) {
        ProjectApplication application = projectApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(Status.REFUSE);
        return projectApplicationRepository.saveAndFlush(application);
    }

    @GetMapping("/filter/date")
    public ResponseEntity<List<Project>> getProjectsByDeliveryDate(@RequestParam LocalDateTime date) {
        List<Project> projects = projectRepository.findByDateLivraison(date);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/filter/budget")
    public ResponseEntity<List<Project>> getProjectsByBudget(
            @RequestParam float minBudget,
            @RequestParam float maxBudget
    ) {
        List<Project> projects = projectRepository.findByBudgetBetween(minBudget, maxBudget);
        return ResponseEntity.ok(projects);
    }

}
