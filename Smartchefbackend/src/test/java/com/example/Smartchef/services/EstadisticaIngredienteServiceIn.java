package com.example.Smartchef.services;

import com.example.Smartchef.servicios.EstadisticaService;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EstadisticaIngredienteServiceIn {

    @Autowired
    private EstadisticaService estadisticaService;

    @Test
    void topIngredientesIntegracionTest() {
        var top = estadisticaService.topIngredientes();
        assertNotNull(top);
    }
}
