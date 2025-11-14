package com.ecfjava.controller;

import com.ecfjava.dto.AddProjectOwnerDTO;
import com.ecfjava.entities.ProjectOwner;
import com.ecfjava.repositories.ProjectOwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProjectOwnerController.class)
public class ProjectOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectOwnerRepository projectOwnerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSignUpProjectOwner() throws Exception {
        AddProjectOwnerDTO dto = new AddProjectOwnerDTO();
        dto.setEmail("owner@test.com");
        dto.setPassword("password");

        ProjectOwner owner = new ProjectOwner();
        owner.setId(1L);
        owner.setEmail(dto.getEmail());
        owner.setPassword(dto.getPassword());

        when(projectOwnerRepository.saveAndFlush(owner)).thenReturn(owner);

        mockMvc.perform(post("/api/ProjectOwner/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("owner@test.com"));
    }

    @Test
    void testSignInProjectOwner() throws Exception {
        AddProjectOwnerDTO dto = new AddProjectOwnerDTO();
        dto.setEmail("owner@test.com");
        dto.setPassword("password");

        ProjectOwner owner = new ProjectOwner();
        owner.setId(1L);
        owner.setEmail(dto.getEmail());
        owner.setPassword(dto.getPassword());

        when(projectOwnerRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()))
                .thenReturn(Optional.of(owner));

        mockMvc.perform(post("/api/ProjectOwner/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("owner@test.com"));
    }
}
