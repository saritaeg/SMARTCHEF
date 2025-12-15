package com.example.Smartchef.modelos;

import jakarta.persistence.*;
@Entity
@Table(name = "favoritos")
@IdClass(FavoritoId.class)
public class Favorito {

    @Id
    private int idUsuario;

    @Id
    private int idReceta;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }
}
