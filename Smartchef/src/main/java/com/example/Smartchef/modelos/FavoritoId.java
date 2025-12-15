package com.example.Smartchef.modelos;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.io.Serializable;

public class FavoritoId implements Serializable {
    private int idUsuario;
    private int idReceta;
}
