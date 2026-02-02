package com.example.Smartchef.modelos;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "planificacion", schema = "public")
public class Planificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlanificacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private LocalDate fecha;

    @OneToMany(mappedBy = "planificacion", cascade = CascadeType.ALL)
    private List<PlanificacionComida> planificacionComidas;

    public Integer getIdPlanificacion() {
        return idPlanificacion;
    }

    public void setIdPlanificacion(Integer idPlanificacion) {
        this.idPlanificacion = idPlanificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<PlanificacionComida> getPlanificacionComidas() {
        return planificacionComidas;
    }

    public void setPlanificacionComidas(List<PlanificacionComida> planificacionComidas) {
        this.planificacionComidas = planificacionComidas;
    }
}
