package com.example.Smartchef.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "planificacion_comida")
public class PlanificacionComida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlanificacionComida;

    @ManyToOne
    @JoinColumn(name = "id_planificacion", nullable = false)
    private Planificacion planificacion;

    @Enumerated(EnumType.STRING)
    private TipoComida tipoComida;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    public enum TipoComida {
        DESAYUNO, ALMUERZO, CENA
    }

    public Integer getIdPlanificacionComida() {
        return idPlanificacionComida;
    }

    public void setIdPlanificacionComida(Integer idPlanificacionComida) {
        this.idPlanificacionComida = idPlanificacionComida;
    }

    public Planificacion getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(Planificacion planificacion) {
        this.planificacion = planificacion;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
