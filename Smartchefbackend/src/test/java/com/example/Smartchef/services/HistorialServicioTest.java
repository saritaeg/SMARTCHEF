package com.example.Smartchef.services;

import com.example.Smartchef.dto.HistorialDTO;
import com.example.Smartchef.modelos.Historial;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.servicios.*;
import com.example.Smartchef.repositorios.IHistorialRepository;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HistorialServicioTest {

    @Autowired
    private HistorialService historialService;

    @Autowired
    private IHistorialRepository historialRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRecetaRepository recetaRepository;

    private Usuario usuario;
    private Receta receta;

    @BeforeAll
    void setup() {
        usuario = new Usuario();
        usuario.setNombre("Usuario Test");
        usuario.setEmail("testhistorial@email.com");
        usuario.setContrasenia("1234");
        usuarioRepository.save(usuario);

        receta = new Receta();
        receta.setNombre("Receta Historial");
        receta.setUsuario(usuario);
        recetaRepository.save(receta);
    }

    @Test
    @DisplayName("Historial -> Registrar receta cocinada Positivo")
    void registrarHistorialPositivoTest() {

        HistorialDTO dto = new HistorialDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setIdReceta(receta.getIdReceta());
        dto.setFecha(LocalDate.now());
        historialService.registrar(dto);
        List<Historial> lista = historialRepository.findByUsuarioIdUsuarioAndFechaBetween(
                usuario.getIdUsuario(),
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1)
        );
        assertFalse(lista.isEmpty(), "El historial debería contener al menos una entrada");
        assertEquals(receta.getIdReceta(), lista.get(0).getReceta().getIdReceta(), "La receta registrada no coincide");
    }

    @Test
    @DisplayName("Historial -> Registrar receta cocinada Negativo (usuario no existe)")
    void registrarHistorialNegativoTest() {

        HistorialDTO dto = new HistorialDTO();
        dto.setIdUsuario(9999);
        dto.setIdReceta(receta.getIdReceta());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> historialService.registrar(dto),
                "Debería lanzar excepción si el usuario no existe");

        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    @DisplayName("Historial -> Consultar historial semanal Positivo")
    void consultarHistorialSemanalPositivoTest() {
        Historial h = new Historial();
        h.setUsuario(usuario);
        h.setReceta(receta);
        h.setFecha(LocalDate.now());
        historialRepository.save(h);
        List<HistorialDTO> resultado = historialService.semana(usuario.getIdUsuario());
        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertFalse(resultado.isEmpty(), "Debería contener registros");
        assertEquals(receta.getNombre(), resultado.get(0).getNombreReceta(), "El nombre de la receta no coincide");
    }

    @Test
    @DisplayName("Historial -> Consultar historial semanal Negativo (sin registros)")
    void consultarHistorialSemanalNegativoTest() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Usuario Sin Historial");
        nuevoUsuario.setEmail("sinhistorial@email.com");
        nuevoUsuario.setContrasenia("1234");
        usuarioRepository.save(nuevoUsuario);
        List<HistorialDTO> resultado = historialService.semana(nuevoUsuario.getIdUsuario());
        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía para usuario sin historial");
    }
}
