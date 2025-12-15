package com.example.Smartchef.controladores;
import com.example.Smartchef.dto.ListaCompraDTO;
import com.example.Smartchef.modelos.ListasCompras;
import com.example.Smartchef.servicios.ListaCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/listas-compra")
public class ListaCompraController {

    private final ListaCompraService listaCompraService;

    public ListaCompraController(ListaCompraService listaCompraService) {
        this.listaCompraService = listaCompraService;
    }

    @PostMapping
    public ResponseEntity<ListaCompraDTO> generarLista(@RequestParam int idReceta) {
        ListaCompraDTO lista = listaCompraService.generarListaDeCompra(idReceta);
        return ResponseEntity.ok(lista);
    }


}
