package com.ecfjava.controller;

import com.ecfjava.entities.Project;
import com.ecfjava.entities.ProjectApplication;
import com.ecfjava.entities.ProjectOwner;
import com.ecfjava.enums.Status;
import com.ecfjava.repositories.ProjectApplicationRepository;
import com.ecfjava.repositories.ProjectOwnerRepository;
import com.ecfjava.repositories.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectOwnerRepository projectOwnerRepository;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private ProjectApplicationRepository projectApplicationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProjects() throws Exception {
        Project p1 = new Project();
        p1.setId(1L);
        p1.setTitre("Projet A");

        Project p2 = new Project();
        p2.setId(2L);
        p2.setTitre("Projet B");

        when(projectRepository.findAll()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/project"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Projet A"))
                .andExpect(jsonPath("$[1].nom").value("Projet B"));
    }

    @Test
    void testAcceptApplication() throws Exception {
        ProjectApplication app = new ProjectApplication();
        app.setId(1L);
        app.setStatus(Status.EN_COURS);

        when(projectApplicationRepository.findById(1L)).thenReturn(Optional.of(app));
        when(projectApplicationRepository.saveAndFlush(any(ProjectApplication.class))).thenReturn(app);

        mockMvc.perform(put("/api/project/applications/1/accept"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACCEPTE"));
    }

    @Test
    void testRejectApplication() throws Exception {
        ProjectApplication app = new ProjectApplication();
        app.setId(1L);
        app.setStatus(Status.EN_COURS);

        when(projectApplicationRepository.findById(1L)).thenReturn(Optional.of(app));
        when(projectApplicationRepository.saveAndFlush(any(ProjectApplication.class))).thenReturn(app);

        mockMvc.perform(put("/api/project/applications/1/reject"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("REFUSE"));
    }
}
