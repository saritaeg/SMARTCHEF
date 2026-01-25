package com.example.Smartchef.services;

import com.example.Smartchef.dto.ListaCompraDTO;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.RecetaIngredientes;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.servicios.ListaCompraService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListasCompraServicioTest {

    @Mock
    private IRecetaRepository recetaRepository;

    @InjectMocks
    private ListaCompraService listaCompraService;

    @Test
    @DisplayName("Generar lista de compra -> Positivo")
    void generarListaDeCompra_RecetaExiste_DevuelveDTO() {
        Receta receta = new Receta();
        receta.setIdReceta(1);
        receta.setRecetaIngredientes(List.of(new RecetaIngredientes()));
        when(recetaRepository.findById(1)).thenReturn(Optional.of(receta));

        ListaCompraDTO dto = listaCompraService.generarListaDeCompra(1);

        assertNotNull(dto, "El DTO de lista de compra no debe ser null");
        assertEquals("Lista de compra", dto.getNombreLista(), "El nombre de la lista no coincide");
        assertFalse(dto.getIngredientes().isEmpty(), "Debe contener ingredientes");
        verify(recetaRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Generar lista de compra -> Negativo (receta no existe)")
    void generarListaDeCompra_RecetaNoExiste_LanzaExcepcion() {

        when(recetaRepository.findById(999)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> listaCompraService.generarListaDeCompra(999),
                "Debería lanzar excepción si la receta no existe");

        assertEquals("Receta no encontrada", exception.getMessage());
        verify(recetaRepository, times(1)).findById(999);
    }
}