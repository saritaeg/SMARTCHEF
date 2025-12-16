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
    private final IListaIngredienteRepository listaIngredienteRepository;

    public ListaCompraService(IListaCompraRepository listaCompraRepository,
                              IRecetaRepository recetaRepository,
                              IRecetaIngredienteRepository recetaIngredienteRepository,
                              IListaIngredienteRepository listaIngredienteRepository) {
        this.listaCompraRepository = listaCompraRepository;
        this.recetaRepository = recetaRepository;
        this.recetaIngredienteRepository = recetaIngredienteRepository;
        this.listaIngredienteRepository = listaIngredienteRepository;
    }

    @Transactional
    public void guardarIngredientesEnListaCompra(int idReceta, Usuario usuario) {
        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        ListasCompras listaCompra = new ListasCompras();
        listaCompra.setUsuario(usuario);
        listaCompra.setNombre("Lista de compra de " + receta.getNombre());

        List<ListaIngrediente> listaIngredientes = new ArrayList<>();
        receta.getRecetaIngredientes().forEach(ri -> {
            ListaIngrediente li = new ListaIngrediente();
            li.setIngrediente(ri.getIngrediente());
            li.setCantidad(ri.getCantidad());
            li.setLista(listaCompra);  // relación bidireccional
            listaIngredientes.add(li);
        });

        listaCompra.setListaIngredientes(listaIngredientes);

        listaCompraRepository.save(listaCompra);
    }

    @Transactional
    public void marcarIngredienteComprado(Integer idListaIngrediente, Boolean comprado) {
        ListaIngrediente li = listaIngredienteRepository.findById(idListaIngrediente)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
        li.setComprado(comprado);
        listaIngredienteRepository.save(li);
    }

    @Transactional
    public void agregarIngredienteManual(Integer idLista, ListaIngrediente nuevoIngrediente) {
        ListasCompras lista = listaCompraRepository.findById(idLista)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        nuevoIngrediente.setLista(lista);
        lista.getListaIngredientes().add(nuevoIngrediente);

        listaCompraRepository.save(lista);
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

    @Transactional
    public List<ListaCompraDTO> obtenerListasUsuario(Integer idUsuario) {
        List<ListasCompras> listas = listaCompraRepository.findAll(); // aquí puedes filtrar por usuario
        List<ListaCompraDTO> dtos = new ArrayList<>();

        for (ListasCompras lista : listas) {
            if (!lista.getUsuario().getIdUsuario().equals(idUsuario)) continue;

            ListaCompraDTO dto = new ListaCompraDTO();
            dto.setNombreLista(lista.getNombre());

            List<ListaCompraDTO.IngredienteListaDTO> ingDTOs = new ArrayList<>();
            for (ListaIngrediente li : lista.getListaIngredientes()) {
                ListaCompraDTO.IngredienteListaDTO i = new ListaCompraDTO.IngredienteListaDTO();
                i.setNombre(li.getIngrediente().getNombre());
                i.setCantidad(li.getCantidad());
                i.setUnidad(li.getIngrediente().getUnidadMedida());

                ingDTOs.add(i);
            }

            dto.setIngredientes(ingDTOs);
            dtos.add(dto);
        }

        return dtos;
    }
}
