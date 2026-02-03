package com.example.Smartchef.controladores;

import com.example.Smartchef.dto.CrearRecetaDTO;
import com.example.Smartchef.dto.RecetaCardDTO;
import com.example.Smartchef.dto.RecetaDetalleDTO;
import com.example.Smartchef.servicios.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://smartchef-gdyy.onrender.com")
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
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarReceta(@PathVariable int id, @RequestBody CrearRecetaDTO dto) {
        recetaService.actualizarReceta(id, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<RecetaCardDTO> buscarRecetas(
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable int id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/favorito")
    public ResponseEntity<Void> marcarFavorito(@PathVariable int id, @RequestParam int idUsuario) {
        recetaService.marcarComoFavorita(idUsuario, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/favorito")
    public ResponseEntity<Void> quitarFavorito(@PathVariable int id, @RequestParam int idUsuario) {
        recetaService.quitarFavorito(idUsuario, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favoritos")
    public List<RecetaCardDTO> obtenerFavoritos(@RequestParam int idUsuario) {
        return recetaService.obtenerFavoritosUsuario(idUsuario);
    }
}
