package com.ecfjava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecfjava.entities.ProjectApplication;
import com.ecfjava.repositories.ProjectApplicationRepository;

@RestController
@RequestMapping("api/projectApplication")
public class ProjectApplicationController {

    private final ProjectApplicationRepository projectApplicationRepository;

    public ProjectApplicationController(ProjectApplicationRepository projectApplicationRepository) {
        this.projectApplicationRepository = projectApplicationRepository;
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<ProjectApplication> getApplicationById(@PathVariable Long id) {
        ProjectApplication application = projectApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        return ResponseEntity.ok(application);
    }


}
