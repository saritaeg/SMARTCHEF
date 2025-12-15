package com.example.Smartchef.controladores;

import com.example.Smartchef.dto.CrearRecetaDTO;
import com.example.Smartchef.dto.RecetaDetalleDTO;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.servicios.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }
    @PostMapping
    public ResponseEntity<Void> crearReceta(@RequestBody CrearRecetaDTO dto) {
        recetaService.crearReceta(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public RecetaDetalleDTO detalle(@PathVariable int id) {
        return recetaService.obtenerDetalle(id);
    }

    @PostMapping("/{id}/favorito")
    public ResponseEntity<Void> marcarFavorito(
            @PathVariable int id,
            @RequestParam int idUsuario
    ) {
        recetaService.marcarComoFavorita(idUsuario, id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public List<Receta> buscarRecetas(
            @RequestParam(required = false) String ingrediente,
            @RequestParam(required = false) Boolean vegetariano,
            @RequestParam(required = false) Boolean sinGluten,
            @RequestParam(required = false) Boolean rapido,
            @RequestParam(required = false) Boolean economico
    ) {
        return recetaService.buscarRecetasConFiltros(
                ingrediente, vegetariano, sinGluten, rapido, economico
        );
    }

}