package com.ecfjava.mapping;

import com.ecfjava.dto.CreerPorteurProjetDTO;
import com.ecfjava.dto.PorteurProjetDTO;
import com.ecfjava.entities.PorteurProjet;

public class PorteurProjetMapper {

    public static PorteurProjetDTO toDTO(PorteurProjet porteurProjet) {

        PorteurProjetDTO dto = new PorteurProjetDTO();

        dto.setId(porteurProjet.getId());
        dto.setNom(porteurProjet.getNom());
        dto.setEmail(porteurProjet.getEmail());
        return dto;
    }

    public static PorteurProjet toEntity(CreerPorteurProjetDTO dto) {

        PorteurProjet porteurProjet = new PorteurProjet();

        porteurProjet.setId(dto.getId());
        porteurProjet.setNom(dto.getNom());
        porteurProjet.setEmail(dto.getEmail());
        porteurProjet.setPassword(dto.getPassword());
        return porteurProjet;
    }
}
