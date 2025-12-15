package com.example.Smartchef.modelos;


import jakarta.persistence.*;

@Entity
@Table(name = "coleccion_recetas")
public class ColeccionReceta {

    @EmbeddedId
    private ColeccionRecetaId id;

    @ManyToOne
    @MapsId("idColeccion")
    @JoinColumn(name = "id_coleccion")
    private Coleccion coleccion;

    @ManyToOne
    @MapsId("idReceta")
    @JoinColumn(name = "id_receta")
    private Receta receta;

    public ColeccionRecetaId getId() {
        return id;
    }

    public void setId(ColeccionRecetaId id) {
        this.id = id;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
