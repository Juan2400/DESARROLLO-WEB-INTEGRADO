package com.colegio.modelo;

public class TipoMatricula {
    private int idTipoMatricula;
    private String nombreTipo;
    private String descripcionTipo;

    public TipoMatricula() {
        
    }

    public int getIdTipoMatricula() {
        return idTipoMatricula;
    }

    public void setIdTipoMatricula(int idTipoMatricula) {
        this.idTipoMatricula = idTipoMatricula;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

}
