package com.example.Smartchef.servicios;

import com.example.Smartchef.dto.HistorialDTO;
import com.example.Smartchef.modelos.Historial;
import com.example.Smartchef.modelos.Receta;
import com.example.Smartchef.modelos.Usuario;
import com.example.Smartchef.repositorios.IHistorialRepository;
import com.example.Smartchef.repositorios.IRecetaRepository;
import com.example.Smartchef.repositorios.IUsuarioRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialService {

    private final IHistorialRepository historialRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IRecetaRepository recetaRepository;

    public HistorialService(IHistorialRepository historialRepository,
                            IUsuarioRepository usuarioRepository,
                            IRecetaRepository recetaRepository) {
        this.historialRepository = historialRepository;
        this.usuarioRepository = usuarioRepository;
        this.recetaRepository = recetaRepository;
    }

    @Transactional
    public void registrar(HistorialDTO dto) {
        Usuario u = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Receta r = recetaRepository.findById(dto.getIdReceta())
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        Historial h = new Historial();
        h.setUsuario(u);
        h.setReceta(r);
        h.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now());

        historialRepository.save(h);
    }

    public List<HistorialDTO> semana(Integer idUsuario) {
        LocalDate hoy = LocalDate.now();
        LocalDate inicio = hoy.minusDays(6);

        List<Historial> lista = historialRepository
                .findByUsuarioIdUsuarioAndFechaBetween(idUsuario, inicio, hoy);

        List<HistorialDTO> dtos = new ArrayList<>();
        for (Historial h : lista) {
            HistorialDTO dto = new HistorialDTO();
            dto.setIdHistorial(h.getIdHistorial());
            dto.setIdUsuario(h.getUsuario().getIdUsuario());
            dto.setIdReceta(h.getReceta().getIdReceta());
            dto.setNombreReceta(h.getReceta().getNombre());
            dto.setFecha(h.getFecha());
            dtos.add(dto);
        }
        return dtos;
    }

}
