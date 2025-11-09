package com.ecfjava.contoller;

import org.antlr.v4.runtime.misc.NotNull;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecfjava.dto.CreerPorteurProjetDTO;
import com.ecfjava.dto.PorteurProjetDTO;
import com.ecfjava.entities.PorteurProjet;
import com.ecfjava.mapping.PorteurProjetMapper;
import com.ecfjava.repositories.PorteurProjetRepository;

import io.micrometer.common.lang.NonNull;

@RestController
@RequestMapping("api/porteurprojet")
public class PorteurProjetController {

    private final PorteurProjetRepository porteurProjetRepository;

    public PorteurProjetController(PorteurProjetRepository porteurProjetRepository) {
        this.porteurProjetRepository = porteurProjetRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<PorteurProjetDTO> createPorteurProjet(@RequestBody CreerPorteurProjetDTO porteurProjetDto) {
        System.err.println("Creating PorteurProjet: " + porteurProjetDto.getNom() + ", Email: " + porteurProjetDto.getEmail() + ", Password: " + porteurProjetDto.getPassword());
        PorteurProjet savedPorteurProjet = porteurProjetRepository.save(PorteurProjetMapper.toEntity(porteurProjetDto));
        return ResponseEntity.ok(PorteurProjetMapper.toDTO(savedPorteurProjet));
    }

}
