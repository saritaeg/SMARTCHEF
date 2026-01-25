package com.example.Smartchef.services;

import com.example.Smartchef.modelos.*;
import com.example.Smartchef.repositorios.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ListaCompraServiceIn {

    @Autowired
    private com.example.Smartchef.servicios.ListaCompraService listaCompraService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRecetaRepository recetaRepository;

    @Autowired
    private IIngredienteRepository ingredienteRepository;

    @Autowired
    private IRecetaIngredienteRepository recetaIngredienteRepository;

    @Autowired
    private IListaCompraRepository listaCompraRepository;

    private Usuario usuario;
    private Receta receta;

    @BeforeEach
    void setup() {
        usuario = new Usuario();
        usuario.setNombre("Usuario Test");
        usuario.setEmail("usuario@test.com");
        usuario.setContrasenia("1234");
        usuarioRepository.save(usuario);

        receta = new Receta();
        receta.setNombre("Receta Test Lista");
        receta.setUsuario(usuario);
        recetaRepository.save(receta);

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Tomate");
        ingrediente.setUnidadMedida("unidad");
        ingredienteRepository.save(ingrediente);

        RecetaIngredientes ri = new RecetaIngredientes();
        ri.setReceta(receta);
        ri.setIngrediente(ingrediente);
        ri.setCantidad(2.0);
        ri.setUnidad("unidad");
        ri.setId(new RecetaIngredienteId(receta.getIdReceta(), ingrediente.getIdIngredientes()));
        recetaIngredienteRepository.save(ri);
    }

    @Test
    void guardarListaCompraIntegracionTest() {
        listaCompraService.guardarIngredientesEnListaCompra(receta.getIdReceta(), usuario);

        List<ListasCompras> listas = listaCompraRepository.findAll();
        assertFalse(listas.isEmpty(), "La lista de compra debería existir");

        ListasCompras listaGuardada = listas.get(0);
        assertEquals(usuario.getIdUsuario(), listaGuardada.getUsuario().getIdUsuario(), "La lista pertenece al usuario correcto");
        assertEquals("Lista de compra de " + receta.getNombre(), listaGuardada.getNombre(), "El nombre de la lista es correcto");
        assertFalse(listaGuardada.getListaIngredientes().isEmpty(), "Debe contener al menos un ingrediente");
        assertEquals("Tomate", listaGuardada.getListaIngredientes().get(0).getIngrediente().getNombre(), "El ingrediente guardado coincide");
    }
}
