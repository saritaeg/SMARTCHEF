package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface IHistorialRepository extends JpaRepository<Historial, Integer> {

    List<Historial> findByUsuarioIdUsuarioAndFechaBetween(Integer idUsuario,
                                                          LocalDate desde,
                                                          LocalDate hasta);
}
