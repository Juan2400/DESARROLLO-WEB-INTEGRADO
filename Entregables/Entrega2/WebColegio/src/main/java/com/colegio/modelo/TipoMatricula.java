package com.colegio.modelo;

public class TipoMatricula {
    private int id_tipo_matricula;
    private String nombre_tipo;
    private String descripcion_tipo;

    public TipoMatricula() {
        
    }

    public TipoMatricula(int id_tipo_matricula, String nombre_tipo, String descripcion_tipo) {
        this.id_tipo_matricula = id_tipo_matricula;
        this.nombre_tipo = nombre_tipo;
        this.descripcion_tipo = descripcion_tipo;
    }

    public int getId_tipo_matricula() {
        return id_tipo_matricula;
    }

    public void setId_tipo_matricula(int id_tipo_matricula) {
        this.id_tipo_matricula = id_tipo_matricula;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public String getDescripcion_tipo() {
        return descripcion_tipo;
    }

    public void setDescripcion_tipo(String descripcion_tipo) {
        this.descripcion_tipo = descripcion_tipo;
    }

    
}
