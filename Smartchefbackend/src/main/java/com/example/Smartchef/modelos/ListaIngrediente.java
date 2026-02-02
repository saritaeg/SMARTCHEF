package com.example.Smartchef.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "lista_ingredientes", schema = "public")
public class ListaIngrediente {

    @EmbeddedId
    private ListaIngredienteId id;

    @ManyToOne
    @MapsId("idLista")
    @JoinColumn(name = "id_lista")
    private ListasCompras lista;

    @ManyToOne
    @MapsId("idIngrediente")
    @JoinColumn(name = "id_ingrediente")
    private Ingrediente ingrediente;

    private Double cantidad;

    private Boolean comprado = false;

    public ListaIngredienteId getId() {
        return id;
    }

    public void setId(ListaIngredienteId id) {
        this.id = id;
    }

    public ListasCompras getLista() {
        return lista;
    }

    public void setLista(ListasCompras lista) {
        this.lista = lista;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getComprado() {
        return comprado;
    }

    public void setComprado(Boolean comprado) {
        this.comprado = comprado;
    }

    public void setIdLista(ListasCompras lc) {
    }

    public void setIdIngrediente(Ingrediente ingrediente) {

    }
}
