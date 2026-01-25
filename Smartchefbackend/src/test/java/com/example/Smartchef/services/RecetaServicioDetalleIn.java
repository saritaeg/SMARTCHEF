package com.example.Smartchef.services;
import com.example.Smartchef.dto.RecetaDetalleDTO;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class RecetaServicioDetalleIn {


    @Autowired
    private com.example.Smartchef.servicios.RecetaService recetaService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRecetaRepository recetaRepository;

    @Test
    void obtenerDetalleRecetaTest() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Luis");
        usuario.setEmail("luis@test.com");
        usuario.setContrasenia("1234");
        usuarioRepository.save(usuario);

        Receta receta = new Receta();
        receta.setNombre("Pizza Test");
        receta.setUsuario(usuario);
        recetaRepository.save(receta);

        RecetaDetalleDTO detalle = recetaService.obtenerDetalle(receta.getIdReceta());
        assertEquals("Pizza Test", detalle.getNombre());
        assertEquals(usuario.getIdUsuario(), detalle.getIdUsuario());
    }
}
