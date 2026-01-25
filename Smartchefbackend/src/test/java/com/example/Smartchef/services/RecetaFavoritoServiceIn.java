package com.example.Smartchef.services;

import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RecetaFavoritoServiceIn {

    @Autowired
    private com.example.Smartchef.servicios.RecetaService recetaService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRecetaRepository recetaRepository;

    @Test
    void marcarYQuitarFavoritoTest() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Marta");
        usuario.setEmail("marta@test.com");
        usuario.setContrasenia("1234");
        usuarioRepository.save(usuario);

        Receta receta = new Receta();
        receta.setNombre("Tarta");
        receta.setUsuario(usuario);
        recetaRepository.save(receta);

        recetaService.marcarComoFavorita(usuario.getIdUsuario(), receta.getIdReceta());
        var favs = recetaService.obtenerFavoritosUsuario(usuario.getIdUsuario());
        assertFalse(favs.isEmpty());

        recetaService.quitarFavorito(usuario.getIdUsuario(), receta.getIdReceta());
        var favsDespues = recetaService.obtenerFavoritosUsuario(usuario.getIdUsuario());
        assertTrue(favsDespues.isEmpty());
    }
}
