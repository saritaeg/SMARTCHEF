package com.example.Smartchef.controladores;

import com.example.Smartchef.dto.HistorialDTO;
import com.example.Smartchef.servicios.HistorialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/historial-cocina")
public class HistorialController {

    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @PostMapping
    public ResponseEntity<Void> registrarHistorial(@RequestBody HistorialDTO dto) {
        historialService.registrar(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/semana/{idUsuario}")
    public ResponseEntity<List<HistorialDTO>> historialSemana(@PathVariable Integer idUsuario) {
        List<HistorialDTO> lista = historialService.semana(idUsuario);
        return ResponseEntity.ok(lista);
    }
}
