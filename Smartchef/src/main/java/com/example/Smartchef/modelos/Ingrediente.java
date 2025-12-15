package com.example.Smartchef.modelos;

import jakarta.persistence.*;  // <- IMPORT PRINCIPAL
import java.util.List;

@Entity
@Table(name = "ingredientes")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngredientes;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String unidadMedida;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    private List<RecetaIngredientes> recetaIngredientes;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    private List<ListaIngrediente> listaIngredientes;

    public enum Categoria {
        CONGELADOS, BEBIDAS, LACTEOS, FRUTAS, OTROS
    }

    // --- getters y setters existentes ---

    public Integer getIdIngredientes() { return idIngredientes; }
    public void setIdIngredientes(Integer idIngredientes) { this.idIngredientes = idIngredientes; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setCategoria(String categoriaStr) {
        if (categoriaStr == null || categoriaStr.isBlank()) {
            this.categoria = Categoria.OTROS;
        } else {
            try {
                this.categoria = Categoria.valueOf(categoriaStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                this.categoria = Categoria.OTROS;
            }
        }
    }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public List<RecetaIngredientes> getRecetaIngredientes() { return recetaIngredientes; }
    public void setRecetaIngredientes(List<RecetaIngredientes> recetaIngredientes) { this.recetaIngredientes = recetaIngredientes; }

    public List<ListaIngrediente> getListaIngredientes() { return listaIngredientes; }
    public void setListaIngredientes(List<ListaIngrediente> listaIngredientes) { this.listaIngredientes = listaIngredientes; }

    // --- NUEVO MÉTODO ---
    public String getCategoriaString() {
        return categoria != null ? categoria.name() : "OTROS";
    }
}
