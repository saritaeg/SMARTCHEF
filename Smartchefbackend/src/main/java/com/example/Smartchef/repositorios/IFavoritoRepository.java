package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.Favorito;
import com.example.Smartchef.modelos.FavoritoId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFavoritoRepository extends JpaRepository<Favorito, FavoritoId> {
    List<Favorito> findByIdUsuario(Integer idUsuario);

    List<Favorito> findByIdReceta(Integer idReceta);

    @Query("""
        SELECT f.idUsuario, COUNT(f.idReceta)
        FROM Favorito f
        GROUP BY f.idUsuario
        ORDER BY COUNT(f.idReceta) DESC
    """)
    List<Object[]> usuariosMasFavoritos();

    @Query("""
        SELECT r.nombre, COUNT(f.idReceta) as veces
        FROM Favorito f
        JOIN Receta r ON r.idReceta = f.idReceta
        WHERE f.idUsuario = :idUsuario
        GROUP BY r.nombre
        ORDER BY veces DESC
    """)
    List<Object[]> topRecetaUsuario(@Param("idUsuario") Integer idUsuario);

    @Modifying
    @Transactional
    @Query("DELETE FROM Favorito f WHERE f.idReceta = :idReceta")
    void deleteByIdReceta(@Param("idReceta") int idReceta);

    List<Favorito> findByIdUsuario(int idUsuario);
}
