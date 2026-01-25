package com.example.Smartchef.services;

import com.example.Smartchef.dto.UsuarioPopularDTO;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IFavoritoRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import com.example.Smartchef.servicios.EstadisticaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstadisticaUsuarioPopularTest {

    @Mock
    private IFavoritoRepository favoritoRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private EstadisticaService estadisticaService;

    @Test
    @DisplayName("Usuario Popular - Caso positivo")
    void usuarioPopularPositivo() {
        List<Object[]> usuariosFavoritos = new ArrayList<>();
        usuariosFavoritos.add(new Object[]{1, 3L});

        when(favoritoRepository.usuariosMasFavoritos())
                .thenReturn(usuariosFavoritos);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Juan");

        when(usuarioRepository.findById(1))
                .thenReturn(Optional.of(usuario));
        List<Object[]> recetaTop = new ArrayList<>();
        recetaTop.add(new Object[]{"Paella"});

        when(favoritoRepository.topRecetaUsuario(1))
                .thenReturn(recetaTop);
        List<UsuarioPopularDTO> resultado = estadisticaService.usuarioPopular();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        UsuarioPopularDTO dto = resultado.get(0);
        assertEquals(1, dto.getIdUsuario());
        assertEquals("Juan", dto.getNombreUsuario());
        assertEquals("Paella", dto.getNombreRecetaMasGuardada());
        assertEquals(3L, dto.getVecesGuardado());
    }

    @Test
    @DisplayName("Usuario Popular - Caso negativo (usuario no existe)")
    void usuarioPopularNegativo() {

        List<Object[]> usuariosFavoritos = new ArrayList<>();
        usuariosFavoritos.add(new Object[]{1, 2L});

        when(favoritoRepository.usuariosMasFavoritos())
                .thenReturn(usuariosFavoritos);

        when(usuarioRepository.findById(1))
                .thenReturn(Optional.empty());

        List<UsuarioPopularDTO> resultado = estadisticaService.usuarioPopular();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}
