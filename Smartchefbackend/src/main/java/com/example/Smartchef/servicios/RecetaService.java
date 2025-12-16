package com.example.Smartchef.servicios;

import com.example.Smartchef.conversores.RecetaMapper;
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
    private final RecetaMapper recetaMapper;

    public RecetaService(
            IRecetaRepository recetaRepository,
            IRecetaIngredienteRepository recetaIngredienteRepository,
            IIngredienteRepository ingredienteRepository,
            IInstruccionRecetaRepository instruccionRecetaRepository,
            IFavoritoRepository favoritoRepository,
            IUsuarioRepository usuarioRepository,
            RecetaMapper recetaMapper
    ) {
        this.recetaRepository = recetaRepository;
        this.recetaIngredienteRepository = recetaIngredienteRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.instruccionRecetaRepository = instruccionRecetaRepository;
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.recetaMapper = recetaMapper;
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

        for (IngredienteCantidadDTO ingDto : dto.getIngredientes()) {
            Ingrediente ingrediente = ingredienteRepository.findByNombre(ingDto.getNombre())
                    .orElseGet(() -> {
                        Ingrediente nuevo = new Ingrediente();
                        nuevo.setNombre(ingDto.getNombre());
                        nuevo.setUnidadMedida(ingDto.getUnidad());
                        nuevo.setCategoria(ingDto.getCategoria());
                        return ingredienteRepository.save(nuevo);
                    });

            RecetaIngredientes ri = new RecetaIngredientes();
            ri.setReceta(recetaGuardada);
            ri.setIngrediente(ingrediente);
            ri.setCantidad(ingDto.getCantidad());
            ri.setUnidad(ingDto.getUnidad());
            ri.setId(new RecetaIngredienteId(recetaGuardada.getIdReceta(), ingrediente.getIdIngredientes()));

            recetaIngredienteRepository.save(ri);
        }

        for (InstruccionDTO instDto : dto.getInstrucciones()) {
            InstruccionReceta instruccion = new InstruccionReceta();
            instruccion.setReceta(recetaGuardada);
            instruccion.setPasoNumero(instDto.getPasoNumero());
            instruccion.setDescripcion(instDto.getDescripcion());
            instruccion.setImagenUrl(instDto.getImagenUrl());

            instruccionRecetaRepository.save(instruccion);
        }
    }
    @Transactional
    public void actualizarReceta(int idReceta, CrearRecetaDTO dto) {
        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        receta.setNombre(dto.getNombre());
        receta.setDescripcion(dto.getDescripcion());
        receta.setTiempoPreparacion(dto.getTiempoPreparacion());
        receta.setVegetariano(dto.isVegetariano());
        receta.setSinGluten(dto.isSinGluten());
        receta.setRapido(dto.isRapido());
        receta.setEconomico(dto.isEconomico());
        receta.setFotoUrl(dto.getFotoUrl());

        recetaRepository.save(receta);

        recetaIngredienteRepository.deleteByRecetaId(idReceta);
        instruccionRecetaRepository.deleteByRecetaId(idReceta);

        for (IngredienteCantidadDTO ingDto : dto.getIngredientes()) {
            Ingrediente ingrediente = ingredienteRepository.findByNombre(ingDto.getNombre())
                    .orElseGet(() -> {
                        Ingrediente nuevo = new Ingrediente();
                        nuevo.setNombre(ingDto.getNombre());
                        nuevo.setUnidadMedida(ingDto.getUnidad());
                        nuevo.setCategoria(ingDto.getCategoria());
                        return ingredienteRepository.save(nuevo);
                    });

            RecetaIngredientes ri = new RecetaIngredientes();
            ri.setReceta(receta);
            ri.setIngrediente(ingrediente);
            ri.setCantidad(ingDto.getCantidad());
            ri.setUnidad(ingDto.getUnidad());
            ri.setId(new RecetaIngredienteId(receta.getIdReceta(), ingrediente.getIdIngredientes()));

            recetaIngredienteRepository.save(ri);
        }

        for (InstruccionDTO instDto : dto.getInstrucciones()) {
            InstruccionReceta instruccion = new InstruccionReceta();
            instruccion.setReceta(receta);
            instruccion.setPasoNumero(instDto.getPasoNumero());
            instruccion.setDescripcion(instDto.getDescripcion());
            instruccion.setImagenUrl(instDto.getImagenUrl());
            instruccionRecetaRepository.save(instruccion);
        }
    }

    @Transactional
    public void eliminarReceta(int idReceta) {

        instruccionRecetaRepository.deleteByRecetaId(idReceta);
        recetaIngredienteRepository.deleteByRecetaId(idReceta);
        favoritoRepository.deleteByIdReceta(idReceta);
        recetaRepository.deleteById(idReceta);
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

        List<RecetaIngredientes> riList = recetaIngredienteRepository.findById_IdReceta(id);
        dto.setIngredientes(riList.stream().map(ri -> {
            IngredienteCantidadDTO ing = new IngredienteCantidadDTO();
            ing.setNombre(ri.getIngrediente().getNombre());
            ing.setCantidad(ri.getCantidad());
            ing.setUnidad(ri.getUnidad());
            ing.setCategoria(ri.getIngrediente().getCategoriaString());
            return ing;
        }).toList());

        dto.setInstrucciones(receta.getInstrucciones().stream().map(inst -> {
            InstruccionDTO i = new InstruccionDTO();
            i.setPasoNumero(inst.getPasoNumero());
            i.setDescripcion(inst.getDescripcion());
            i.setImagenUrl(inst.getImagenUrl());
            return i;
        }).toList());

        return dto;
    }

    public List<Receta> buscarPorFiltros(Boolean vegetariano,
                                         Boolean sinGluten,
                                         Boolean rapido,
                                         Boolean economico) {
        return recetaRepository.findByFiltros(vegetariano, sinGluten, rapido, economico);
    }

    public List<RecetaCardDTO> buscarRecetasConFiltros(
            String ingrediente,
            Boolean vegetariano,
            Boolean sinGluten,
            Boolean rapido,
            Boolean economico
    ) {
        if (ingrediente != null && ingrediente.isBlank()) ingrediente = null;

        return recetaRepository
                .buscarRecetasPorIngredienteYPreferencias(
                        ingrediente, vegetariano, sinGluten, rapido, economico
                )
                .stream()
                .map(recetaMapper::toCardDTO)
                .toList();
    }
    @Transactional
    public void marcarComoFavorita(int idUsuario, int idReceta) {
        Favorito favorito = new Favorito();
        favorito.setIdUsuario(idUsuario);
        favorito.setIdReceta(idReceta);
        favoritoRepository.save(favorito);
    }

    @Transactional
    public void quitarFavorito(int idUsuario, int idReceta) {
        FavoritoId id = new FavoritoId();
        id.setIdUsuario(idUsuario);
        id.setIdReceta(idReceta);
        favoritoRepository.deleteById(id);
    }

    public List<RecetaCardDTO> obtenerFavoritosUsuario(int idUsuario) {
        List<Favorito> favs = favoritoRepository.findByIdUsuario(idUsuario);
        List<Receta> recetas = favs.stream()
                .map(f -> recetaRepository.findById(f.getIdReceta()).orElse(null))
                .filter(r -> r != null)
                .toList();
        return recetas.stream()
                .map(recetaMapper::toCardDTO)
                .toList();
    }
}
