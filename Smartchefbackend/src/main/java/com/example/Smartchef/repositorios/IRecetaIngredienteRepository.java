package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.RecetaIngredientes;
import com.example.Smartchef.modelos.RecetaIngredienteId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecetaIngredienteRepository extends JpaRepository<RecetaIngredientes, RecetaIngredienteId> {
    List<RecetaIngredientes> findById_IdReceta(Integer idReceta);
    @Query("""
        SELECT ri.ingrediente.nombre, COUNT(DISTINCT ri.receta.idReceta)
        FROM RecetaIngredientes ri
        GROUP BY ri.ingrediente.nombre
        ORDER BY COUNT(DISTINCT ri.receta.idReceta) DESC
    """)
    List<Object[]> topIngredientes();
    @Modifying
    @Transactional
    @Query("DELETE FROM RecetaIngredientes ri WHERE ri.receta.idReceta = :idReceta")
    void deleteByRecetaId(@Param("idReceta") int idReceta);

}
