package com.example.Smartchef.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecetaDetalleDTO {
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    private Integer tiempoPreparacion;
    private Boolean vegetariano;
    private Boolean sinGluten;
    private Boolean rapido;
    private Boolean economico;
    private String fotoUrl;
    private Integer idUsuario;

    private List<IngredienteCantidadDTO> ingredientes;
    private List<InstruccionDTO> instrucciones;
}
