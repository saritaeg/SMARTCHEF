package com.example.Smartchef.modelos;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ListaIngredienteId implements Serializable {

    private Integer idLista;
    private Integer idIngrediente;

    public ListaIngredienteId() {}

    public ListaIngredienteId(Integer idLista, Integer idIngrediente) {
        this.idLista = idLista;
        this.idIngrediente = idIngrediente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListaIngredienteId)) return false;
        ListaIngredienteId that = (ListaIngredienteId) o;
        return Objects.equals(idLista, that.idLista) &&
                Objects.equals(idIngrediente, that.idIngrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLista, idIngrediente);
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }
}
