package com.example.Smartchef.services;

import com.example.Smartchef.dto.RecetaCardDTO;
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
public class RecetaServicioBuscarTest {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private EntityManager entityManager;

    private Receta receta1;
    private Receta receta2;

    @BeforeAll
    void cargarDatos() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario Test");
        usuario.setEmail("test@email.com");
        usuario.setContrasenia("1234");
        entityManager.persist(usuario);
        receta1 = new Receta();
        receta1.setNombre("Ensalada de Tomate");
        receta1.setVegetariano(true);
        receta1.setSinGluten(true);
        receta1.setUsuario(usuario);
        entityManager.persist(receta1);

        Ingrediente ingrediente1 = new Ingrediente();
        ingrediente1.setNombre("Tomate");
        entityManager.persist(ingrediente1);

        RecetaIngredientes ri1 = new RecetaIngredientes();
        ri1.setReceta(receta1);
        ri1.setIngrediente(ingrediente1);
        ri1.setCantidad(2.0);
        ri1.setUnidad("unidades");
        ri1.setId(new RecetaIngredienteId(receta1.getIdReceta(), ingrediente1.getIdIngredientes()));
        entityManager.persist(ri1);

        receta2 = new Receta();
        receta2.setNombre("Pollo al horno");
        receta2.setVegetariano(false);
        receta2.setSinGluten(true);
        receta2.setUsuario(usuario);
        entityManager.persist(receta2);

        Ingrediente ingrediente2 = new Ingrediente();
        ingrediente2.setNombre("Pollo");
        entityManager.persist(ingrediente2);

        RecetaIngredientes ri2 = new RecetaIngredientes();
        ri2.setReceta(receta2);
        ri2.setIngrediente(ingrediente2);
        ri2.setCantidad(1.0);
        ri2.setUnidad("kg");
        ri2.setId(new RecetaIngredienteId(receta2.getIdReceta(), ingrediente2.getIdIngredientes()));
        entityManager.persist(ri2);
    }

    @Test
    @DisplayName("Servicio -> Buscar Recetas con Filtro Positivo")
    public void buscarRecetasConFiltroPositivoTest() {

        String ingrediente = "Tomate";
        Boolean vegetariano = true;

        List<RecetaCardDTO> resultados = recetaService.buscarRecetasConFiltros(
                ingrediente, vegetariano, null, null, null
        );

        assertNotNull(resultados, "La lista no debería ser nula");
        assertEquals(1, resultados.size(), "Debería devolver solo una receta");
        assertEquals("Ensalada de Tomate", resultados.get(0).getNombre(), "El nombre de la receta no coincide");
    }

    @Test
    @DisplayName("Servicio -> Buscar Recetas con Filtro Negativo")
    public void buscarRecetasConFiltroNegativoTest() {
        String ingrediente = "Chocolate";

        List<RecetaCardDTO> resultados = recetaService.buscarRecetasConFiltros(
                ingrediente, null, null, null, null
        );
        assertNotNull(resultados, "La lista no debería ser nula");
        assertTrue(resultados.isEmpty(), "La lista debería estar vacía para un ingrediente inexistente");
    }
}
