package com.example.Smartchef.services;

import com.example.Smartchef.dto.CrearRecetaDTO;
import com.example.Smartchef.dto.IngredienteCantidadDTO;
import com.example.Smartchef.dto.InstruccionDTO;
import com.example.Smartchef.servicios.*;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IIngredienteRepository;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecetaServicioTest {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private IRecetaRepository recetaRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IIngredienteRepository ingredienteRepository;

    private Usuario usuarioExistente;

    @BeforeAll
    void setup() {
        usuarioExistente = new Usuario();
        usuarioExistente.setNombre("Usuario Prueba");
        usuarioExistente.setEmail("usuario@prueba.com");
        usuarioExistente.setContrasenia("1234");
        usuarioRepository.save(usuarioExistente);
    }

    @Test
    @DisplayName("Recetas POST -> Positivo")
    void crearRecetaPositivoTest() {
        CrearRecetaDTO dto = new CrearRecetaDTO();
        dto.setIdUsuario(usuarioExistente.getIdUsuario());
        dto.setNombre("Receta Test");
        dto.setDescripcion("Descripción test");
        dto.setTiempoPreparacion(30);
        dto.setVegetariano(true);
        dto.setSinGluten(false);
        dto.setRapido(true);
        dto.setEconomico(false);
        dto.setFotoUrl("url_foto_test");
        IngredienteCantidadDTO ingrediente = new IngredienteCantidadDTO();
        ingrediente.setNombre("Tomate");
        ingrediente.setCantidad(2.0);
        ingrediente.setUnidad("unidad");
        ingrediente.setCategoria("Verdura");
        dto.setIngredientes(List.of(ingrediente));
        InstruccionDTO instruccion = new InstruccionDTO();
        instruccion.setPasoNumero(1);
        instruccion.setDescripcion("Cortar los tomates");
        instruccion.setImagenUrl("url_paso_1");
        dto.setInstrucciones(List.of(instruccion));

        recetaService.crearReceta(dto);

        Receta recetaGuardada = recetaRepository.findByUsuario_IdUsuario(usuarioExistente.getIdUsuario())
                .stream()
                .filter(r -> r.getNombre().equals("Receta Test"))
                .findFirst()
                .orElse(null);

        assertNotNull(recetaGuardada, "La receta debería haberse guardado");
        assertEquals(1, recetaGuardada.getRecetaIngredientes().size(), "Debe tener 1 ingrediente");
        assertEquals(1, recetaGuardada.getInstrucciones().size(), "Debe tener 1 instrucción");
        assertTrue(recetaGuardada.getVegetariano(), "La receta debería ser vegetariana");
        assertFalse(recetaGuardada.getSinGluten(), "La receta no debería ser sin gluten");
        assertTrue(recetaGuardada.getRapido(), "La receta debería ser rápida");

    }

    @Test
    @DisplayName("Recetas POST -> Negativo")
    void crearRecetaNegativoTest() {
        CrearRecetaDTO dto = new CrearRecetaDTO();
        dto.setIdUsuario(9999);
        dto.setNombre("Receta Fallida");

        RuntimeException excepcion = assertThrows(RuntimeException.class,
                () -> recetaService.crearReceta(dto),
                "Debe lanzar excepción si el usuario no existe");

        assertEquals("Usuario no encontrado", excepcion.getMessage());
    }
}
