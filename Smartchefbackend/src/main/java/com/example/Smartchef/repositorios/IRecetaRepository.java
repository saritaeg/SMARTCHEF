package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRecetaRepository extends JpaRepository<Receta, Integer> {

    // Buscar recetas por filtros
    @Query("SELECT r FROM Receta r WHERE " +
            "(:vegetariano IS NULL OR r.vegetariano = :vegetariano) AND " +
            "(:sinGluten IS NULL OR r.sinGluten = :sinGluten) AND " +
            "(:rapido IS NULL OR r.rapido = :rapido) AND " +
            "(:economico IS NULL OR r.economico = :economico)")
    List<Receta> findByFiltros(@Param("vegetariano") Boolean vegetariano,
                               @Param("sinGluten") Boolean sinGluten,
                               @Param("rapido") Boolean rapido,
                               @Param("economico") Boolean economico);

    // Buscar recetas por ingrediente
    @Query("SELECT DISTINCT r FROM Receta r JOIN r.recetaIngredientes ri JOIN ri.ingrediente i " +
            "WHERE UPPER(i.nombre) LIKE UPPER(CONCAT('%', :nombre, '%'))")
    List<Receta> findByIngredienteNombre(@Param("nombre") String nombre);

    // Buscar recetas por nombre
    List<Receta> findByNombreContainingIgnoreCase(String nombre);

    List<Receta> findByUsuario_IdUsuario(Integer idUsuario);
    @Query("""
        SELECT DISTINCT r 
        FROM Receta r
        LEFT JOIN r.recetaIngredientes ri
        LEFT JOIN ri.ingrediente i
        WHERE (:vegetariano IS NULL OR r.vegetariano = :vegetariano)
          AND (:rapido IS NULL OR r.rapido = :rapido)
          AND (:ingrediente IS NULL OR i.nombre LIKE %:ingrediente%)
    """)
    List<Receta> buscarConFiltros(
            @Param("vegetariano") Boolean vegetariano,
            @Param("rapido") Boolean rapido,
            @Param("ingrediente") String ingrediente
    );
    @Query("""
SELECT DISTINCT r
FROM Receta r
LEFT JOIN r.recetaIngredientes ri
LEFT JOIN ri.ingrediente i
WHERE (:vegetariano IS NULL OR r.vegetariano = :vegetariano)
  AND (:sinGluten IS NULL OR r.sinGluten = :sinGluten)
  AND (:rapido IS NULL OR r.rapido = :rapido)
  AND (:economico IS NULL OR r.economico = :economico)
  AND (:ingrediente IS NULL OR i.nombre IS NULL OR UPPER(i.nombre) LIKE UPPER(CONCAT('%', :ingrediente, '%')))
""")
    List<Receta> buscarRecetasPorIngredienteYPreferencias(
            @Param("ingrediente") String ingrediente,
            @Param("vegetariano") Boolean vegetariano,
            @Param("sinGluten") Boolean sinGluten,
            @Param("rapido") Boolean rapido,
            @Param("economico") Boolean economico
    );


}
