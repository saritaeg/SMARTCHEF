package com.example.Smartchef.services;

import com.example.Smartchef.dto.CrearUsuarioDTO;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import com.example.Smartchef.servicios.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UsuarioServiceIn {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Test
    void crearUsuarioIntegracionTest() {
        CrearUsuarioDTO dto = new CrearUsuarioDTO();
        dto.setNombre("Test Usuario");
        dto.setEmail("testusuario@correo.com");
        dto.setContrasenia("1234");

        usuarioService.crearUsuario(dto);

        Optional<Usuario> guardado = usuarioRepository.findByEmailAndContrasenia("testusuario@correo.com","1234");
        assertTrue(guardado.isPresent(), "El usuario debería guardarse en la base de datos");
        assertEquals("Test Usuario", guardado.get().getNombre());
    }
}
