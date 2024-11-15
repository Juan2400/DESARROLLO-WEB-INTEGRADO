package com.colegio.modelo;

public class Grado {
    private int id_grado;
    private String nombre_grado;
    private String descripcion_grado;

    public Grado() {
    }
    
    public int getId_grado() {
        return id_grado;
    }

    public void setId_grado(int id_grado) {
        this.id_grado = id_grado;
    }

    public String getNombre_grado() {
        return nombre_grado;
    }

    public void setNombre_grado(String nombre_grado) {
        this.nombre_grado = nombre_grado;
    }

    public String getDescripcion_grado() {
        return descripcion_grado;
    }

    public void setDescripcion_grado(String descripcion_grado) {
        this.descripcion_grado = descripcion_grado;
    }

    
}