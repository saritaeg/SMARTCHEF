package com.example.Smartchef.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historial")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    private LocalDate fecha;

    // Getters y setters
    public Integer getIdHistorial() { return idHistorial; }
    public void setIdHistorial(Integer idHistorial) { this.idHistorial = idHistorial; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Receta getReceta() { return receta; }
    public void setReceta(Receta receta) { this.receta = receta; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
