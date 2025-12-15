package com.example.Smartchef.dto;

import lombok.Data;
import java.util.List;

@Data
public class CrearUsuarioDTO {
    private String nombre;
    private String email;
    private String contrasenia;
    private List<String> preferencias;
}
