package com.example.Smartchef.controladores;

import com.example.Smartchef.dto.EstadisticaIngredienteDTO;
import com.example.Smartchef.dto.UsuarioPopularDTO;
import com.example.Smartchef.servicios.EstadisticaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaController {

    private final EstadisticaService estadisticaService;


    @GetMapping("/usuarioPopular")
    public ResponseEntity<List<UsuarioPopularDTO>> usuarioPopular() {
        List<UsuarioPopularDTO> lista = estadisticaService.usuarioPopular();
        return ResponseEntity.ok(lista);
    }
    public EstadisticaController(EstadisticaService estadisticaService) {
        this.estadisticaService = estadisticaService;
    }

    @GetMapping("/ingredientes")
    public ResponseEntity<List<EstadisticaIngredienteDTO>> topIngredientes() {
        return ResponseEntity.ok(estadisticaService.topIngredientes());
    }
}
