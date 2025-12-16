package com.example.Smartchef.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "receta_ingredientes")
public class RecetaIngredientes {

    @EmbeddedId
    private RecetaIngredienteId id;

    @ManyToOne
    @MapsId("idReceta")
    @JoinColumn(name = "id_receta")
    private Receta receta;

    @ManyToOne
    @MapsId("idIngrediente")
    @JoinColumn(name = "id_ingredientes")
    private Ingrediente ingrediente;

    private Double cantidad;
    private String unidad;

    public RecetaIngredienteId getId() { return id; }
    public void setId(RecetaIngredienteId id) { this.id = id; }

    public Receta getReceta() { return receta; }
    public void setReceta(Receta receta) { this.receta = receta; }

    public Ingrediente getIngrediente() { return ingrediente; }
    public void setIngrediente(Ingrediente ingrediente) { this.ingrediente = ingrediente; }

    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
}
