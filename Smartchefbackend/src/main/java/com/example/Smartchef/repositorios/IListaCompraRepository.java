package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.ListasCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IListaCompraRepository extends JpaRepository<ListasCompras, Integer> {
}
