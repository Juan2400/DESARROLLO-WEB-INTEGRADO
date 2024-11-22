package com.colegio.modelo;

public class Grado {
    private int idGrado;
    private String nombreGrado;
    private String descripcionGrado;

    public Grado() {
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreGrado(String nombreGrado) {
        this.nombreGrado = nombreGrado;
    }

    public String getDescripcionGrado() {
        return descripcionGrado;
    }

    public void setDescripcionGrado(String descripcionGrado) {
        this.descripcionGrado = descripcionGrado;
    }
    
}