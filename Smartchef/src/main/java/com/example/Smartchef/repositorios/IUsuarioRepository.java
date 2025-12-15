package com.example.Smartchef.repositorios;

import com.example.Smartchef.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {}
