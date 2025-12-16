package com.example.Smartchef.dto;

import lombok.Data;

@Data
public class UsuarioPopularDTO {
    private Integer idUsuario;
    private String nombreUsuario;
    private String nombreRecetaMasGuardada;
    private Long vecesGuardado;
}

