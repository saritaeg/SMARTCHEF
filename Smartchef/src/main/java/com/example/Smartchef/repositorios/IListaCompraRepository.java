package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.ListasCompras;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IListaCompraRepository extends JpaRepository<ListasCompras, Integer> {
}
