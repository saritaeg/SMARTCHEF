package com.example.Smartchef.services;

import com.example.Smartchef.dto.HistorialDTO;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import com.example.Smartchef.servicios.HistorialService;
import com.example.Smartchef.modelos.Historial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HistorialSemanaServiceIn {

    @Autowired
    private HistorialService historialService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRecetaRepository recetaRepository;

    @Test
    void consultarHistorialSemanalIntegracionTest() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Clara");
        usuario.setEmail("clara@test.com");
        usuario.setContrasenia("1234");
        usuarioRepository.save(usuario);

        Receta receta = new Receta();
        receta.setNombre("Guiso");
        receta.setUsuario(usuario);
        recetaRepository.save(receta);

        HistorialDTO dto = new HistorialDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setIdReceta(receta.getIdReceta());
        historialService.registrar(dto);

        var historial = historialService.semana(usuario.getIdUsuario());
        assertFalse(historial.isEmpty());
    }
}
