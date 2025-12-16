package com.example.Smartchef.dto;

import lombok.Data;

import java.util.List;

@Data
public class CrearRecetaDTO {
    private Integer idUsuario;
    private String nombre;
    private String descripcion;
    private Integer tiempoPreparacion;
    private boolean vegetariano;
    private boolean sinGluten;
    private boolean rapido;
    private boolean economico;
    private String fotoUrl;

    private List<IngredienteCantidadDTO> ingredientes;
    private List<InstruccionDTO> instrucciones;
}
