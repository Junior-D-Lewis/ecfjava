package com.ecfjava.controller;

import com.ecfjava.dto.AddDevelopperDTO;
import com.ecfjava.dto.DevelopperDTO;
import com.ecfjava.entities.Developper;
import com.ecfjava.entities.Project;
import com.ecfjava.entities.ProjectApplication;
import com.ecfjava.enums.Status;
import com.ecfjava.repositories.DevelopperRepository;
import com.ecfjava.repositories.ProjectApplicationRepository;
import com.ecfjava.repositories.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DevelopperController.class)
public class DevelopperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DevelopperRepository developperRepository;

    @MockBean
    private ProjectApplicationRepository projectApplicationRepository;

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSignUpDevelopper() throws Exception {
        AddDevelopperDTO dto = new AddDevelopperDTO();
        dto.setEmail("test@test.com");
        dto.setPassword("password");

        Developper dev = new Developper();
        dev.setId(1L);
        dev.setEmail(dto.getEmail());
        dev.setPassword(dto.getPassword());

        when(developperRepository.saveAndFlush(any(Developper.class))).thenReturn(dev);

        mockMvc.perform(post("/api/developpeur/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@test.com"));
    }

    @Test
    void testSignInDevelopper() throws Exception {
        AddDevelopperDTO dto = new AddDevelopperDTO();
        dto.setEmail("test@test.com");
        dto.setPassword("password");

        Developper dev = new Developper();
        dev.setId(1L);
        dev.setEmail(dto.getEmail());
        dev.setPassword(dto.getPassword());

        when(developperRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()))
                .thenReturn(Optional.of(dev));

        mockMvc.perform(post("/api/developpeur/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@test.com"));
    }

    @Test
    void testApplyToProject() throws Exception {
        Developper dev = new Developper();
        dev.setId(1L);

        Project project = new Project();
        project.setId(1L);

        ProjectApplication app = new ProjectApplication();
        app.setId(1L);
        app.setDevelopper(dev);
        app.setProject(project);
        app.setStatus(Status.EN_COURS);
        app.setDateCandidature(LocalDateTime.now());

        when(developperRepository.findById(1L)).thenReturn(Optional.of(dev));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectApplicationRepository.saveAndFlush(any(ProjectApplication.class))).thenReturn(app);

        mockMvc.perform(post("/api/developpeur/projects/1/apply")
                        .param("developerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EN_COURS"));
    }

    @Test
    void testValidateApplication() throws Exception {
        ProjectApplication app = new ProjectApplication();
        app.setId(1L);
        app.setStatus(Status.EN_COURS);

        when(projectApplicationRepository.findById(1L)).thenReturn(Optional.of(app));
        when(projectApplicationRepository.saveAndFlush(any(ProjectApplication.class))).thenReturn(app);

        mockMvc.perform(put("/api/developpeur/applications/1/validate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("VALIDE"));
    }
}
