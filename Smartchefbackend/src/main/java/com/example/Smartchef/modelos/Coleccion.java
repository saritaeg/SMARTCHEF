package com.example.Smartchef.modelos;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "colecciones")
public class Coleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColeccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private String nombre;

    @OneToMany(mappedBy = "coleccion", cascade = CascadeType.ALL)
    private List<ColeccionReceta> coleccionRecetas;

    public Integer getIdColeccion() {
        return idColeccion;
    }

    public void setIdColeccion(Integer idColeccion) {
        this.idColeccion = idColeccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ColeccionReceta> getColeccionRecetas() {
        return coleccionRecetas;
    }

    public void setColeccionRecetas(List<ColeccionReceta> coleccionRecetas) {
        this.coleccionRecetas = coleccionRecetas;
    }
}
