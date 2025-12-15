package com.example.Smartchef.modelos;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;

    private String nombre;
    private String descripcion;
    private Integer tiempoPreparacion;

    private Boolean vegetariano = false;
    private Boolean sinGluten = false;
    private Boolean rapido = false;
    private Boolean economico = false;

    private String fotoUrl;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecetaIngredientes> recetaIngredientes;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InstruccionReceta> instrucciones;

    // Constructor vacío
    public Receta() {}

    // Getters y setters
    public Integer getIdReceta() { return idReceta; }
    public void setIdReceta(Integer idReceta) { this.idReceta = idReceta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getTiempoPreparacion() { return tiempoPreparacion; }
    public void setTiempoPreparacion(Integer tiempoPreparacion) { this.tiempoPreparacion = tiempoPreparacion; }

    public Boolean getVegetariano() { return vegetariano; }
    public void setVegetariano(Boolean vegetariano) { this.vegetariano = vegetariano; }

    public Boolean getSinGluten() { return sinGluten; }
    public void setSinGluten(Boolean sinGluten) { this.sinGluten = sinGluten; }

    public Boolean getRapido() { return rapido; }
    public void setRapido(Boolean rapido) { this.rapido = rapido; }

    public Boolean getEconomico() { return economico; }
    public void setEconomico(Boolean economico) { this.economico = economico; }

    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<RecetaIngredientes> getRecetaIngredientes() { return recetaIngredientes; }
    public void setRecetaIngredientes(List<RecetaIngredientes> recetaIngredientes) { this.recetaIngredientes = recetaIngredientes; }

    public List<InstruccionReceta> getInstrucciones() { return instrucciones; }
    public void setInstrucciones(List<InstruccionReceta> instrucciones) { this.instrucciones = instrucciones; }

    // Conveniencia: obtener lista de ingredientes directamente
    public List<Ingrediente> getIngredientes() {
        if (recetaIngredientes == null) return List.of();
        return recetaIngredientes.stream()
                .map(RecetaIngredientes::getIngrediente)
                .toList();
    }
}
