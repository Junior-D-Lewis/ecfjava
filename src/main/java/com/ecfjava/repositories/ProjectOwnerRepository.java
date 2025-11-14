package com.ecfjava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecfjava.entities.ProjectOwner;

public interface ProjectOwnerRepository extends JpaRepository<ProjectOwner, Long> {

    Optional<ProjectOwner> findByEmailAndPassword(String email, String password);

}
