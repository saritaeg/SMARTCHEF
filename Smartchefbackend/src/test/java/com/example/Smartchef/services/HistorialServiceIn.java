package com.example.Smartchef.services;

import com.example.Smartchef.dto.HistorialDTO;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import com.example.Smartchef.servicios.HistorialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HistorialServiceIn {

    @Autowired
    private HistorialService historialService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRecetaRepository recetaRepository;

    @Test
    void registrarHistorialIntegracionTest() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Pedro");
        usuario.setEmail("pedro@test.com");
        usuario.setContrasenia("1234");
        usuarioRepository.save(usuario);

        Receta receta = new Receta();
        receta.setNombre("Sopa");
        receta.setUsuario(usuario);
        recetaRepository.save(receta);

        HistorialDTO dto = new HistorialDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setIdReceta(receta.getIdReceta());
        dto.setFecha(LocalDate.now());

        historialService.registrar(dto);
        var lista = historialService.semana(usuario.getIdUsuario());
        assertFalse(lista.isEmpty());
    }
}
