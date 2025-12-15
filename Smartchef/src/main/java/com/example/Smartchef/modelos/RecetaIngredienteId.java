package com.example.Smartchef.modelos;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecetaIngredienteId implements Serializable {

    private Integer idReceta;
    private Integer idIngrediente;

    public RecetaIngredienteId() {}

    public RecetaIngredienteId(Integer idReceta, Integer idIngrediente) {
        this.idReceta = idReceta;
        this.idIngrediente = idIngrediente;
    }

    // Getters y setters
    public Integer getIdReceta() { return idReceta; }
    public void setIdReceta(Integer idReceta) { this.idReceta = idReceta; }

    public Integer getIdIngrediente() { return idIngrediente; }
    public void setIdIngrediente(Integer idIngrediente) { this.idIngrediente = idIngrediente; }

    // hashCode y equals (obligatorio para @EmbeddedId)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecetaIngredienteId that)) return false;
        return Objects.equals(idReceta, that.idReceta) &&
                Objects.equals(idIngrediente, that.idIngrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceta, idIngrediente);
    }
}
