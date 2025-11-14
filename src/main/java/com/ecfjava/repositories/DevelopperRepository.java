package com.ecfjava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecfjava.entities.Developper;

public interface DevelopperRepository extends JpaRepository <Developper, Long> {
    Optional<Developper> findByEmailAndPassword(String email, String password);

}
