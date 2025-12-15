package com.example.Smartchef.servicios;

import com.example.Smartchef.dto.ListaCompraDTO;
import com.example.Smartchef.modelos.*;
import com.example.Smartchef.repositorios.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaCompraService {

    private final IListaCompraRepository listaCompraRepository;
    private final IRecetaRepository recetaRepository;
    private final IRecetaIngredienteRepository recetaIngredienteRepository;

    public ListaCompraService(IListaCompraRepository listaCompraRepository,
                              IRecetaRepository recetaRepository,
                              IRecetaIngredienteRepository recetaIngredienteRepository) {
        this.listaCompraRepository = listaCompraRepository;
        this.recetaRepository = recetaRepository;
        this.recetaIngredienteRepository = recetaIngredienteRepository;
    }

    @Transactional
    public ListaCompraDTO generarListaDeCompra(int idReceta) {
        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        ListaCompraDTO dto = new ListaCompraDTO();
        dto.setNombreLista("Lista de compra");

        List<ListaCompraDTO.IngredienteListaDTO> ingredientes = receta.getRecetaIngredientes().stream().map(ri -> {
            ListaCompraDTO.IngredienteListaDTO i = new ListaCompraDTO.IngredienteListaDTO();
            i.setNombre(ri.getIngrediente().getNombre());
            i.setCantidad(ri.getCantidad());
            i.setUnidad(ri.getUnidad());
            return i;
        }).toList();

        dto.setIngredientes(ingredientes);

        return dto;
    }


}
