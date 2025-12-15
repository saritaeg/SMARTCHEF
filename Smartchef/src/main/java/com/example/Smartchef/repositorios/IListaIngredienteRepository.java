package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.ListaIngrediente;
import com.example.Smartchef.modelos.ListaIngredienteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IListaIngredienteRepository extends JpaRepository<ListaIngrediente, ListaIngredienteId> {

    List<ListaIngrediente> findById_IdLista(Integer idLista);
}
