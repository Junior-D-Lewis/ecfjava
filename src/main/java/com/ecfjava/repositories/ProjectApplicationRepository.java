package com.ecfjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecfjava.entities.ProjectApplication;

public interface ProjectApplicationRepository extends JpaRepository <ProjectApplication, Long> {

}
