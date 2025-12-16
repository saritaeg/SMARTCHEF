package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.InstruccionReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IInstruccionRecetaRepository extends JpaRepository<InstruccionReceta, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM InstruccionReceta i WHERE i.receta.idReceta = :idReceta")
    void deleteByRecetaId(@Param("idReceta") int idReceta);

}
