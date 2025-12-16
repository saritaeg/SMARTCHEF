package com.example.Smartchef.servicios;

import com.example.Smartchef.dto.EstadisticaIngredienteDTO;
import com.example.Smartchef.dto.UsuarioPopularDTO;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IFavoritoRepository;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class EstadisticaService {

    private final IFavoritoRepository favoritoRepository;
    private final IRecetaRepository recetaRepository;
    private final IUsuarioRepository usuarioRepository;

    public List<EstadisticaIngredienteDTO> topIngredientes() {
        Map<String, Long> contador = new HashMap<>();
        List<Receta> recetas = recetaRepository.findAll();
        for (Receta r : recetas) {
            r.getRecetaIngredientes().forEach(ri -> {
                String nombre = ri.getIngrediente().getNombre();
                contador.put(nombre, contador.getOrDefault(nombre, 0L) + 1L);
            });
        }

        List<EstadisticaIngredienteDTO> lista = new ArrayList<>();
        contador.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .forEach(e -> {
                    EstadisticaIngredienteDTO dto = new EstadisticaIngredienteDTO();
                    dto.setNombreIngrediente(e.getKey());
                    dto.setUsos(e.getValue());
                    lista.add(dto);
                });

        return lista;
    }

    public List<UsuarioPopularDTO> usuarioPopular() {
        List<Object[]> resultados = favoritoRepository.usuariosMasFavoritos();
        List<UsuarioPopularDTO> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            Integer idUsuario = (Integer) fila[0];
            Long total = (Long) fila[1];

            Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
            if (usuario == null) continue;

            List<Object[]> recetaMasGuardada = favoritoRepository.topRecetaUsuario(idUsuario);
            String nombreReceta = recetaMasGuardada.isEmpty() ? "" : (String) recetaMasGuardada.get(0)[0];

            UsuarioPopularDTO dto = new UsuarioPopularDTO();
            dto.setIdUsuario(idUsuario);
            dto.setNombreUsuario(usuario.getNombre());
            dto.setNombreRecetaMasGuardada(nombreReceta);
            dto.setVecesGuardado(total);

            lista.add(dto);
        }

        return lista;
    }
}
