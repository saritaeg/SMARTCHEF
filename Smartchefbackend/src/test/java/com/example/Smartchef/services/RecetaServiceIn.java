package com.example.Smartchef.services;

import com.example.Smartchef.dto.CrearRecetaDTO;
import com.example.Smartchef.dto.IngredienteCantidadDTO;
import com.example.Smartchef.dto.InstruccionDTO;
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
public class RecetaServiceIn {

    @Autowired
    private com.example.Smartchef.servicios.RecetaService recetaService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRecetaRepository recetaRepository;

    @Test
    void crearRecetaIntegracionTest() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@test.com");
        usuario.setContrasenia("1234");
        usuarioRepository.save(usuario);

        CrearRecetaDTO dto = new CrearRecetaDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre("Receta Test");
        dto.setDescripcion("Descripcion test");
        dto.setTiempoPreparacion(15);
        dto.setVegetariano(true);
        dto.setSinGluten(false);
        dto.setRapido(true);
        dto.setEconomico(true);
        dto.setIngredientes(List.of(new IngredienteCantidadDTO(){{
            setNombre("Tomate"); setCantidad(2.0); setUnidad("unidad"); setCategoria("Verdura");
        }}));
        dto.setInstrucciones(List.of(new InstruccionDTO(){{
            setPasoNumero(1); setDescripcion("Cortar tomates"); setImagenUrl(null);
        }}));

        recetaService.crearReceta(dto);

        assertFalse(recetaRepository.findByNombreContainingIgnoreCase("Receta Test").isEmpty());
    }
}
