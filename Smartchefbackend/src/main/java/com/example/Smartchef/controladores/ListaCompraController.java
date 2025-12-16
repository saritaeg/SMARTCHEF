package com.example.Smartchef.controladores;
import com.example.Smartchef.dto.ListaCompraDTO;
import com.example.Smartchef.modelos.ListaIngrediente;
import com.example.Smartchef.modelos.ListasCompras;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import com.example.Smartchef.servicios.ListaCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/listas-compra")
public class ListaCompraController {

    private final ListaCompraService listaCompraService;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    public ListaCompraController(ListaCompraService listaCompraService) {
        this.listaCompraService = listaCompraService;
    }

    @PostMapping
    public ResponseEntity<ListaCompraDTO> generarLista(@RequestParam int idReceta) {
        ListaCompraDTO lista = listaCompraService.generarListaDeCompra(idReceta);
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/agregar-receta")
    public ResponseEntity<Void> agregarRecetaALista(@RequestParam int idReceta) {
        Usuario usuario = usuarioRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        listaCompraService.guardarIngredientesEnListaCompra(idReceta, usuario);

        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<ListaCompraDTO>> obtenerListas(@RequestParam Integer idUsuario) {
        List<ListaCompraDTO> listas = listaCompraService.obtenerListasUsuario(idUsuario);
        return ResponseEntity.ok(listas);
    }

    @PatchMapping("/marcar-comprado/{idListaIngrediente}")
    public ResponseEntity<Void> marcarComprado(
            @PathVariable Integer idListaIngrediente,
            @RequestParam Boolean comprado) {

        listaCompraService.marcarIngredienteComprado(idListaIngrediente, comprado);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/agregar-ingrediente")
    public ResponseEntity<Void> agregarIngredienteManual(
            @RequestParam Integer idLista,
            @RequestBody ListaIngrediente nuevoIngrediente) {

        listaCompraService.agregarIngredienteManual(idLista, nuevoIngrediente);
        return ResponseEntity.ok().build();
    }

}
