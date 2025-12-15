package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IIngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    Optional<Ingrediente> findByNombreIgnoreCase(String nombre);
    Optional<Ingrediente> findByNombre(String nombre);

}