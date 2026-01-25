package com.example.Smartchef.services;

import com.example.Smartchef.modelos.Favorito;
import com.example.Smartchef.repositorios.IFavoritoRepository;
import com.example.Smartchef.servicios.RecetaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RecetaFavoritoServicioTest {

    @Mock
    private IFavoritoRepository favoritoRepository;

    @InjectMocks
    private RecetaService recetaService;

    @Test
    @DisplayName("Marcar receta como favorita -> Positivo")
    void marcarRecetaComoFavorita_Positivo() {
        int idUsuario = 1;
        int idReceta = 100;
        recetaService.marcarComoFavorita(idUsuario, idReceta);
        verify(favoritoRepository, times(1)).save(any(Favorito.class));
    }

    @Test
    @DisplayName("Marcar receta como favorita -> Negativo (error al guardar)")
    void marcarRecetaComoFavorita_Negativo() {
        int idUsuario = 1;
        int idReceta = 100;
        doThrow(new RuntimeException("Error al guardar"))
                .when(favoritoRepository).save(any(Favorito.class));
        RuntimeException excepcion = assertThrows(RuntimeException.class,
                () -> recetaService.marcarComoFavorita(idUsuario, idReceta),
                "Debería lanzar excepción si hay error al guardar");

        assertEquals("Error al guardar", excepcion.getMessage());
    }
}