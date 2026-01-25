package com.example.Smartchef.services;

import com.example.Smartchef.servicios.EstadisticaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EstadisticaUsuarioPopularServiceIn {

    @Autowired
    private EstadisticaService estadisticaService;

    @Test
    void usuarioPopularIntegracionTest() {
        var lista = estadisticaService.usuarioPopular();
        assertNotNull(lista);
    }
}
