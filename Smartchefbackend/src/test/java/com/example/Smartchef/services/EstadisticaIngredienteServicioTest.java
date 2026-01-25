package com.example.Smartchef.services;

import com.example.Smartchef.dto.EstadisticaIngredienteDTO;
import com.example.Smartchef.modelos.Ingrediente;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.RecetaIngredienteId;
import com.example.Smartchef.servicios.*;
import com.example.Smartchef.modelos.RecetaIngredientes;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IRecetaIngredienteRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EstadisticaIngredienteServicioTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EstadisticaService estadisticaService;

    @Autowired
    private IRecetaRepository recetaRepository;

    @Autowired
    private IRecetaIngredienteRepository recetaIngredienteRepository;

    private Receta receta1;
    private Receta receta2;

    @BeforeAll
    void setup() {
        receta1 = new Receta();
        receta1.setNombre("Receta 1");
        recetaRepository.save(receta1);

        Ingrediente ing1 = new Ingrediente();
        ing1.setNombre("Tomate");
        entityManager.persist(ing1);

        RecetaIngredientes ri1 = new RecetaIngredientes();
        ri1.setReceta(receta1);
        ri1.setIngrediente(ing1);
        ri1.setCantidad(2.0);
        ri1.setUnidad("unidades");
        ri1.setId(new RecetaIngredienteId(receta1.getIdReceta(), ing1.getIdIngredientes()));
        recetaIngredienteRepository.save(ri1);

        receta2 = new Receta();
        receta2.setNombre("Receta 2");
        recetaRepository.save(receta2);

        Ingrediente ing2 = new Ingrediente();
        ing2.setNombre("Lechuga");
        entityManager.persist(ing2);

        RecetaIngredientes ri2 = new RecetaIngredientes();
        ri2.setReceta(receta2);
        ri2.setIngrediente(ing2);
        ri2.setCantidad(1.0);
        ri2.setUnidad("unidad");
        ri2.setId(new RecetaIngredienteId(receta2.getIdReceta(), ing2.getIdIngredientes()));
        recetaIngredienteRepository.save(ri2);
    }

    @Test
    @DisplayName("Estadística Ingredientes -> Positivo: Devuelve top ingredientes")
    void topIngredientesPositivoTest() {
        List<EstadisticaIngredienteDTO> resultado = estadisticaService.topIngredientes();
        assertNotNull(resultado, "La lista no debería ser nula");
        assertEquals(2, resultado.size(), "Debería devolver 2 ingredientes");
        assertEquals("Tomate", resultado.get(0).getNombreIngrediente(), "El primer ingrediente debería ser Tomate");
        assertEquals(1L, resultado.get(1).getUsos(), "La Lechuga debería tener 1 uso");
    }

    @Test
    @DisplayName("Estadística Ingredientes -> Negativo: Sin recetas devuelve lista vacía")
    void topIngredientesNegativoTest() {
        recetaIngredienteRepository.deleteAll();
        recetaRepository.deleteAll();
        List<EstadisticaIngredienteDTO> resultado = estadisticaService.topIngredientes();
        assertNotNull(resultado, "La lista no debería ser nula");
        assertTrue(resultado.isEmpty(), "Debería estar vacía al no haber recetas");
    }
}
