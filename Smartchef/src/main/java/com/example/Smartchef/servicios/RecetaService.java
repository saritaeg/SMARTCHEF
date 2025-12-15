package com.example.Smartchef.servicios;

import com.example.Smartchef.dto.*;
import com.example.Smartchef.modelos.*;
import com.example.Smartchef.repositorios.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    private final IRecetaRepository recetaRepository;
    private final IRecetaIngredienteRepository recetaIngredienteRepository;
    private final IIngredienteRepository ingredienteRepository;
    private final IInstruccionRecetaRepository instruccionRecetaRepository;
    private final IFavoritoRepository favoritoRepository;
    private final IUsuarioRepository usuarioRepository;

    public RecetaService(
            IRecetaRepository recetaRepository,
            IRecetaIngredienteRepository recetaIngredienteRepository,
            IIngredienteRepository ingredienteRepository,
            IInstruccionRecetaRepository instruccionRecetaRepository,
            IFavoritoRepository favoritoRepository,
            IUsuarioRepository usuarioRepository
    ) {
        this.recetaRepository = recetaRepository;
        this.recetaIngredienteRepository = recetaIngredienteRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.instruccionRecetaRepository = instruccionRecetaRepository;
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void crearReceta(CrearRecetaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Receta receta = new Receta();
        receta.setNombre(dto.getNombre());
        receta.setDescripcion(dto.getDescripcion());
        receta.setTiempoPreparacion(dto.getTiempoPreparacion());
        receta.setVegetariano(dto.isVegetariano());
        receta.setSinGluten(dto.isSinGluten());
        receta.setRapido(dto.isRapido());
        receta.setEconomico(dto.isEconomico());
        receta.setUsuario(usuario);
        receta.setFotoUrl(dto.getFotoUrl());

        Receta recetaGuardada = recetaRepository.save(receta);

        // Ingredientes
        for (IngredienteCantidadDTO ingDto : dto.getIngredientes()) {
            // Buscar ingrediente por nombre
            Ingrediente ingrediente = ingredienteRepository.findByNombre(ingDto.getNombre())
                    .orElseGet(() -> {
                        Ingrediente nuevo = new Ingrediente();
                        nuevo.setNombre(ingDto.getNombre());
                        nuevo.setUnidadMedida(ingDto.getUnidad()); // CORRECTO
                        nuevo.setCategoria(ingDto.getCategoria()); // String convertido a enum internamente
                        return ingredienteRepository.save(nuevo);
                    });

            // Crear relación receta-ingrediente
            RecetaIngredientes ri = new RecetaIngredientes();
            ri.setReceta(recetaGuardada);
            ri.setIngrediente(ingrediente);
            ri.setCantidad(ingDto.getCantidad()); // Double
            ri.setUnidad(ingDto.getUnidad());
            ri.setId(new RecetaIngredienteId(recetaGuardada.getIdReceta(), ingrediente.getIdIngredientes()));

            recetaIngredienteRepository.save(ri);
        }


        // Instrucciones
        for (InstruccionDTO instDto : dto.getInstrucciones()) {
            InstruccionReceta instruccion = new InstruccionReceta();
            instruccion.setReceta(recetaGuardada);
            instruccion.setPasoNumero(instDto.getPasoNumero());
            instruccion.setDescripcion(instDto.getDescripcion());
            instruccion.setImagenUrl(instDto.getImagenUrl());

            instruccionRecetaRepository.save(instruccion);
        }
    }

    public List<Receta> buscarPorFiltros(Boolean vegetariano,
                                         Boolean sinGluten,
                                         Boolean rapido,
                                         Boolean economico) {
        return recetaRepository.findByFiltros(vegetariano, sinGluten, rapido, economico);
    }

    public RecetaDetalleDTO obtenerDetalle(int id) {
        Receta receta = recetaRepository.findById(id).orElseThrow();

        RecetaDetalleDTO dto = new RecetaDetalleDTO();
        dto.setIdReceta(receta.getIdReceta());
        dto.setNombre(receta.getNombre());
        dto.setDescripcion(receta.getDescripcion());
        dto.setTiempoPreparacion(receta.getTiempoPreparacion());
        dto.setVegetariano(receta.getVegetariano());
        dto.setSinGluten(receta.getSinGluten());
        dto.setRapido(receta.getRapido());
        dto.setEconomico(receta.getEconomico());
        dto.setFotoUrl(receta.getFotoUrl());
        dto.setIdUsuario(receta.getUsuario().getIdUsuario());

        // Ingredientes con nombre
        // Ingredientes con nombre y categoría como String
        List<RecetaIngredientes> riList = recetaIngredienteRepository.findById_IdReceta(id);
        dto.setIngredientes(riList.stream().map(ri -> {
            IngredienteCantidadDTO ing = new IngredienteCantidadDTO();
            ing.setNombre(ri.getIngrediente().getNombre());
            ing.setCantidad(ri.getCantidad());
            ing.setUnidad(ri.getUnidad());
            ing.setCategoria(ri.getIngrediente().getCategoriaString()); // <-- convierte enum a String
            return ing;
        }).toList());


        // Instrucciones
        dto.setInstrucciones(receta.getInstrucciones().stream().map(inst -> {
            InstruccionDTO i = new InstruccionDTO();
            i.setPasoNumero(inst.getPasoNumero());
            i.setDescripcion(inst.getDescripcion());
            i.setImagenUrl(inst.getImagenUrl());
            return i;
        }).toList());

        return dto;
    }

    @Transactional
    public void marcarComoFavorita(int idUsuario, int idReceta) {
        Favorito favorito = new Favorito();
        favorito.setIdUsuario(idUsuario);
        favorito.setIdReceta(idReceta);
        favoritoRepository.save(favorito);
    }
    public List<Receta> buscarRecetasConFiltros(String ingrediente, Boolean vegetariano,
                                                Boolean sinGluten, Boolean rapido, Boolean economico) {
        if (ingrediente != null && ingrediente.isBlank()) {
            ingrediente = null; // evita que la query devuelva todo
        }
        return recetaRepository.buscarRecetasPorIngredienteYPreferencias(
                ingrediente, vegetariano, sinGluten, rapido, economico
        );
    }


}
