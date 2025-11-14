package com.ecfjava.controller;

import com.ecfjava.entities.ProjectApplication;
import com.ecfjava.repositories.ProjectApplicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectApplicationController.class)
public class ProjectApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectApplicationRepository projectApplicationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetApplicationById() throws Exception {
        ProjectApplication app = new ProjectApplication();
        app.setId(1L);

        when(projectApplicationRepository.findById(1L)).thenReturn(Optional.of(app));

        mockMvc.perform(get("/api/projectApplication/applications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
