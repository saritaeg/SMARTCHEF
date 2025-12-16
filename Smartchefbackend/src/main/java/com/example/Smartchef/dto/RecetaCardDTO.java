package com.example.Smartchef.dto;

import lombok.Data;

@Data
public class RecetaCardDTO {
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    private Integer tiempoPreparacion;
    private Boolean vegetariano;
    private Boolean sinGluten;
    private Boolean rapido;
    private Boolean economico;
    private String fotoUrl;
}
