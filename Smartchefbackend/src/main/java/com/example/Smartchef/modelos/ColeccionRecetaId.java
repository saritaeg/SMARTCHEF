package com.example.Smartchef.modelos;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ColeccionRecetaId implements Serializable {

    private Integer idColeccion;
    private Integer idReceta;

    public ColeccionRecetaId() {}

    public ColeccionRecetaId(Integer idColeccion, Integer idReceta) {
        this.idColeccion = idColeccion;
        this.idReceta = idReceta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColeccionRecetaId)) return false;
        ColeccionRecetaId that = (ColeccionRecetaId) o;
        return Objects.equals(idColeccion, that.idColeccion) &&
                Objects.equals(idReceta, that.idReceta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idColeccion, idReceta);
    }

    public Integer getIdColeccion() {
        return idColeccion;
    }

    public void setIdColeccion(Integer idColeccion) {
        this.idColeccion = idColeccion;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }
}
