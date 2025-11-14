package com.ecfjava.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecfjava.dto.AddDevelopperDTO;
import com.ecfjava.dto.AddProjectOwnerDTO;
import com.ecfjava.dto.DevelopperDTO;
import com.ecfjava.dto.ProjectApplicationDTO;
import com.ecfjava.dto.ProjectOwnerDTO;
import com.ecfjava.entities.Developper;
import com.ecfjava.entities.Project;
import com.ecfjava.entities.ProjectApplication;
import com.ecfjava.entities.ProjectOwner;
import com.ecfjava.enums.Status;
import com.ecfjava.mapping.DevelopperMapper;
import com.ecfjava.mapping.ProjectOwnerMapper;
import com.ecfjava.repositories.DevelopperRepository;
import com.ecfjava.repositories.ProjectApplicationRepository;
import com.ecfjava.repositories.ProjectOwnerRepository;
import com.ecfjava.repositories.ProjectRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/developpeur")
public class DevelopperController {

    private final DevelopperRepository developperRepository;
    private final ProjectApplicationRepository projectApplicationRepository;
    private final ProjectRepository projectRepository;

    public DevelopperController(DevelopperRepository developperRepository, ProjectApplicationRepository projectApplicationRepository, ProjectRepository projectRepository) {
        this.developperRepository = developperRepository;
        this.projectApplicationRepository = projectApplicationRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<DevelopperDTO> signUpDevelopper(@RequestBody AddDevelopperDTO developperDto) {
        Developper savedDevelopper = developperRepository.saveAndFlush(DevelopperMapper.toEntity(developperDto));
        return ResponseEntity.ok(DevelopperMapper.toDTO(savedDevelopper));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<DevelopperDTO> signInDevelopper(@RequestBody AddDevelopperDTO developperDto) {
        Developper developper = developperRepository.findByEmailAndPassword(
                developperDto.getEmail(),
                developperDto.getPassword()
        ).orElseThrow(() -> new RuntimeException("Invalid credentials"));

        return ResponseEntity.ok(DevelopperMapper.toDTO(developper));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DevelopperDTO> updateDeveloper(@PathVariable Long id, @RequestBody DevelopperDTO developperDTO) {
        Developper developper = developperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found"));

        developper = DevelopperMapper.toEntity(developperDTO);


        return developperRepository.saveAndFlush(developper) != null
                ? ResponseEntity.ok(DevelopperMapper.toDTO(developper))
                : ResponseEntity.status(500).build();
    }

    @PostMapping("/projects/{projectId}/apply")
    public ResponseEntity<ProjectApplication> applyToProject(@PathVariable Long projectId, @RequestParam Long developerId){
        ProjectApplication application = new ProjectApplication();
        Developper developper = developperRepository.findById(developerId)
                .orElseThrow(() -> new RuntimeException("Developer not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        application.setDevelopper(developper);
        application.setProject(project);
        application.setDateCandidature(java.time.LocalDateTime.now());
        application.setStatus(Status.EN_COURS);

        projectApplicationRepository.saveAndFlush(application);

        return ResponseEntity.ok(application);

    }

    @PutMapping("/applications/{applicationId}/validate")
    public ResponseEntity<ProjectApplication> validateAcceptedApplication(@PathVariable Long applicationId) {
        ProjectApplication application = projectApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(Status.VALIDE);
        projectApplicationRepository.saveAndFlush(application);

        return ResponseEntity.ok(application);

    }

}
