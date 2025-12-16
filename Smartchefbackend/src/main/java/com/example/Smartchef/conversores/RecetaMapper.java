package com.example.Smartchef.conversores;

import com.example.Smartchef.dto.RecetaCardDTO;
import com.example.Smartchef.modelos.Receta;
import org.springframework.stereotype.Component;

@Component
public class RecetaMapper {

    public RecetaCardDTO toCardDTO(Receta receta) {
        RecetaCardDTO dto = new RecetaCardDTO();
        dto.setIdReceta(receta.getIdReceta());
        dto.setNombre(receta.getNombre());
        dto.setDescripcion(receta.getDescripcion());
        dto.setTiempoPreparacion(receta.getTiempoPreparacion());
        dto.setVegetariano(receta.getVegetariano());
        dto.setSinGluten(receta.getSinGluten());
        dto.setRapido(receta.getRapido());
        dto.setEconomico(receta.getEconomico());
        dto.setFotoUrl(receta.getFotoUrl());
        return dto;
    }
}
