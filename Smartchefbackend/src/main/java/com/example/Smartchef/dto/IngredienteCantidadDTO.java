package com.example.Smartchef.dto;

import lombok.Data;

@Data
public class IngredienteCantidadDTO {
    private String nombre;
    private Double cantidad;
    private String unidad;
    private String categoria;
}
