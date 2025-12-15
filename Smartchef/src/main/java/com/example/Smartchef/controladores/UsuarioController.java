package com.example.Smartchef.controladores;

import com.example.Smartchef.dto.CrearUsuarioDTO;
import com.example.Smartchef.servicios.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
@CrossOrigin
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/crear")
    public void crearUsuario(@RequestBody CrearUsuarioDTO dto){
        service.crearUsuario(dto);
    }
}
