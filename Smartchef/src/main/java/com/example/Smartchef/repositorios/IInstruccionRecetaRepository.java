package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.InstruccionReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInstruccionRecetaRepository
        extends JpaRepository<InstruccionReceta, Integer> {
}
