package com.example.Smartchef.services;
import com.example.Smartchef.dto.CrearUsuarioDTO;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.Smartchef.servicios.UsuarioService;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioServicioTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private EntityManager entityManager;

    private CrearUsuarioDTO usuarioValido;

    @BeforeAll
    void setup() {
        usuarioValido = new CrearUsuarioDTO();
        usuarioValido.setNombre("Ana");
        usuarioValido.setEmail("ana@email.com");
        usuarioValido.setContrasenia("1234");
        usuarioValido.setPreferencias(java.util.List.of("vegetariano", "sin_gluten"));
    }


    @Test
    @DisplayName("Usuario POST -> Positivo")
    void crearUsuarioPositivoTest() {
        usuarioService.crearUsuario(usuarioValido);

        Usuario usuarioEnDB = usuarioRepository
                .findByEmailAndContrasenia("ana@email.com", "1234")
                .orElse(null);

        assertNotNull(usuarioEnDB, "El usuario debería haberse guardado correctamente");
        assertTrue(usuarioEnDB.getVegetariano(), "El usuario debería ser vegetariano");
        assertTrue(usuarioEnDB.getSinGluten(), "El usuario debería ser sin gluten");
    }


    @Test
    @DisplayName("Usuario POST -> Negativo")
    void crearUsuarioNegativoTest() {
        usuarioService.crearUsuario(usuarioValido);

        CrearUsuarioDTO usuarioDuplicado = new CrearUsuarioDTO();
        usuarioDuplicado.setNombre("Ana2");
        usuarioDuplicado.setEmail("ana@email.com");
        usuarioDuplicado.setContrasenia("abcd");

        assertThrows(Exception.class, () -> usuarioService.crearUsuario(usuarioDuplicado),
                "Debería lanzar excepción al intentar registrar usuario con email duplicado");
    }

}
