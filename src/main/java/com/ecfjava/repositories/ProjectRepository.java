package com.ecfjava.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecfjava.entities.Project;

public interface ProjectRepository extends JpaRepository <Project, Long> {
    
    List<Project> findByDateLivraison(LocalDateTime date);

    List<Project> findByBudgetBetween(Float min, Float max);    
}
