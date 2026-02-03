package com.example.Smartchef.controladores;

import com.example.Smartchef.dto.CrearUsuarioDTO;
import com.example.Smartchef.servicios.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
@CrossOrigin(origins = "https://smartchef-1-sdnt.onrender.com")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/crear")
    public void crearUsuario(@RequestBody CrearUsuarioDTO dto){
        service.crearUsuario(dto);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CrearUsuarioDTO dto) {
        boolean existe = service.validarUsuario(dto.getEmail(), dto.getContrasenia());
        if (existe) {
            return ResponseEntity.ok("Login correcto");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
        }
    }

}
