package com.example.Smartchef.modelos;


import jakarta.persistence.*;

@Entity
@Table(name = "instrucciones_receta", schema = "public")
public class InstruccionReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInstruccion;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    private Integer pasoNumero;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String imagenUrl;

    public Integer getIdInstruccion() {
        return idInstruccion;
    }

    public void setIdInstruccion(Integer idInstruccion) {
        this.idInstruccion = idInstruccion;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Integer getPasoNumero() {
        return pasoNumero;
    }

    public void setPasoNumero(Integer pasoNumero) {
        this.pasoNumero = pasoNumero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setIdReceta(Receta saved) {

    }
}
