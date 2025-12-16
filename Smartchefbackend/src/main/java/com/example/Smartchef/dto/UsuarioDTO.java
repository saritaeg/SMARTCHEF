package com.example.Smartchef.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombre;
    private String email;
    private Boolean vegetariano;
    private Boolean sinGluten;
    private Boolean rapido;

}
