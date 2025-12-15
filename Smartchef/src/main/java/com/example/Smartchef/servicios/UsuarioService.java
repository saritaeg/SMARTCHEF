package com.example.Smartchef.servicios;

import com.example.Smartchef.dto.CrearUsuarioDTO;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public void crearUsuario(CrearUsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasenia(dto.getContrasenia());

        if (dto.getPreferencias() != null) {
            for (String pref : dto.getPreferencias()) {
                switch (pref.toLowerCase()) {
                    case "vegetariano" -> usuario.setVegetariano(true);
                    case "sin_gluten" -> usuario.setSinGluten(true);
                }
            }
        }

        usuarioRepository.save(usuario);
    }
}
