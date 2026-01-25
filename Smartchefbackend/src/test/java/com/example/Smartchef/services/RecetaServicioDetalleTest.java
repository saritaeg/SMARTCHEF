package com.example.Smartchef.services;

import com.example.Smartchef.dto.InstruccionDTO;
import com.example.Smartchef.dto.IngredienteCantidadDTO;
import com.example.Smartchef.dto.RecetaDetalleDTO;
import com.example.Smartchef.modelos.*;
import com.example.Smartchef.servicios.*;
import jakarta.persistence.EntityManager;
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
public class RecetaServicioDetalleTest {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private EntityManager entityManager;

    private Receta receta;

    @BeforeAll
    void cargarDatos() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario Test");
        usuario.setEmail("test2@email.com");
        usuario.setContrasenia("1234");
        entityManager.persist(usuario);
        receta = new Receta();
        receta.setNombre("Sopa de Verduras");
        receta.setDescripcion("Receta saludable");
        receta.setTiempoPreparacion(30);
        receta.setVegetariano(true);
        receta.setSinGluten(true);
        receta.setUsuario(usuario);
        entityManager.persist(receta);

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Zanahoria");
        ingrediente.setUnidadMedida("unidades");
        entityManager.persist(ingrediente);

        RecetaIngredientes ri = new RecetaIngredientes();
        ri.setReceta(receta);
        ri.setIngrediente(ingrediente);
        ri.setCantidad(2.0);
        ri.setUnidad("unidades");
        ri.setId(new RecetaIngredienteId(receta.getIdReceta(), ingrediente.getIdIngredientes()));
        entityManager.persist(ri);
        InstruccionReceta instr = new InstruccionReceta();
        instr.setReceta(receta);
        instr.setPasoNumero(1);
        instr.setDescripcion("Cortar las verduras");
        entityManager.persist(instr);
    }

    @Test
    @DisplayName("Servicio -> Obtener Detalle de Receta Positivo")
    public void obtenerDetalleRecetaPositivoTest() {

        int idReceta = receta.getIdReceta();

        RecetaDetalleDTO detalle = recetaService.obtenerDetalle(idReceta);

        assertNotNull(detalle, "El detalle de la receta no debería ser nulo");
        assertEquals("Sopa de Verduras", detalle.getNombre(), "El nombre de la receta no coincide");
        assertEquals(1, detalle.getIngredientes().size(), "Debería tener un ingrediente");
        assertEquals(1, detalle.getInstrucciones().size(), "Debería tener una instrucción");
    }

    @Test
    @DisplayName("Servicio -> Obtener Detalle de Receta Negativo")
    public void obtenerDetalleRecetaNegativoTest() {
        int idRecetaInexistente = 999;
        assertThrows(RuntimeException.class,
                () -> recetaService.obtenerDetalle(idRecetaInexistente),
                "Debería lanzar excepción si la receta no existe");
    }
}
