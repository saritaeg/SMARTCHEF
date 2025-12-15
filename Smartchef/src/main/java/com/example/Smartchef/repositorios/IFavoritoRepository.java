package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.Favorito;
import com.example.Smartchef.modelos.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFavoritoRepository extends JpaRepository<Favorito, FavoritoId> {

    // Buscar todos los favoritos de un usuario
    List<Favorito> findByIdUsuario(Integer idUsuario);

    // Buscar todos los favoritos de una receta
    List<Favorito> findByIdReceta(Integer idReceta);

    // Usuarios con más recetas guardadas
    @Query("""
        SELECT f.idUsuario, COUNT(f.idReceta)
        FROM Favorito f
        GROUP BY f.idUsuario
        ORDER BY COUNT(f.idReceta) DESC
    """)
    List<Object[]> usuariosMasFavoritos();

    // Top receta más guardada de un usuario con el nombre de la receta
    @Query("""
        SELECT r.nombre, COUNT(f.idReceta) as veces
        FROM Favorito f
        JOIN Receta r ON r.idReceta = f.idReceta
        WHERE f.idUsuario = :idUsuario
        GROUP BY r.nombre
        ORDER BY veces DESC
    """)
    List<Object[]> topRecetaUsuario(@Param("idUsuario") Integer idUsuario);
}
