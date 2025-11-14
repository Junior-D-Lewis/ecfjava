package com.ecfjava.mapping;

import com.ecfjava.dto.AddDevelopperDTO;
import com.ecfjava.dto.DevelopperDTO;
import com.ecfjava.entities.Developper;

public class DevelopperMapper {

    public static Developper toEntity(AddDevelopperDTO developpeur) {
        if (developpeur == null) return null;

        Developper entity = new Developper();
        entity.setNom(developpeur.getNom());
        entity.setEmail(developpeur.getEmail());
        entity.setPassword(developpeur.getPassword());
        entity.setDescription(developpeur.getDescription());
        entity.setTechnosMaitrisees(developpeur.getTechnosMaitrisees());
        entity.setAnciennete(developpeur.getAnciennete());
        return entity;
    }

    public static Developper toEntity(DevelopperDTO developpeur) {
        if (developpeur == null) return null;
            
        Developper entity = new Developper();
        entity.setNom(developpeur.getNom());
        entity.setEmail(developpeur.getEmail());
        entity.setDescription(developpeur.getDescription());
        entity.setTechnosMaitrisees(developpeur.getTechnosMaitrisees());
        entity.setAnciennete(developpeur.getAnciennete());
        return entity;
    }

    public static DevelopperDTO toDTO(Developper developpeur) {
        if (developpeur == null) {
            return null;
        }
        DevelopperDTO dto = new DevelopperDTO();
        dto.setId(developpeur.getId());
        dto.setNom(developpeur.getNom());
        dto.setEmail(developpeur.getEmail());
        dto.setDescription(developpeur.getDescription());
        dto.setTechnosMaitrisees(developpeur.getTechnosMaitrisees());
        dto.setAnciennete(developpeur.getAnciennete());
        return dto;
    }

}
