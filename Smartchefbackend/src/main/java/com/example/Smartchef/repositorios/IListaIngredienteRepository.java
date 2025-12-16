package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.ListaIngrediente;
import com.example.Smartchef.modelos.ListaIngredienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IListaIngredienteRepository extends JpaRepository<ListaIngrediente, Integer> {

    List<ListaIngrediente> findById_IdLista(Integer idLista);
}
