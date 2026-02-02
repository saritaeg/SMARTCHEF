package com.example.Smartchef.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "listas_compras",schema = "public")
public class ListasCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLista;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private LocalDate fechaCreacion = LocalDate.now();

    private String nombre = "mi lista";

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    private List<ListaIngrediente> listaIngredientes = new ArrayList<>();


    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ListaIngrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(List<ListaIngrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }


}
