package com.example.Smartchef.dto;

import java.util.List;

public class ListaCompraDTO {

    private String nombreLista;
    private List<IngredienteListaDTO> ingredientes;

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public List<IngredienteListaDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteListaDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public static class IngredienteListaDTO {
        private String nombre;
        private Double cantidad;
        private String unidad;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Double getCantidad() {
            return cantidad;
        }

        public void setCantidad(Double cantidad) {
            this.cantidad = cantidad;
        }

        public String getUnidad() {
            return unidad;
        }

        public void setUnidad(String unidad) {
            this.unidad = unidad;
        }
    }
}
