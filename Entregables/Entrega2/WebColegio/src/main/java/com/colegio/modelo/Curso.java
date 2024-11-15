package com.colegio.modelo;

public class Curso {
    private int id_curso;
    private String nombre_curso;
    private String descripcion_curso;

    public Curso() {
        
    }
    
    // Constructor
    public Curso(int id_curso, String nombre_curso, String descripcion_curso) {
        this.id_curso = id_curso;
        this.nombre_curso = nombre_curso;
        this.descripcion_curso = descripcion_curso;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public String getDescripcion_curso() {
        return descripcion_curso;
    }

    public void setDescripcion_curso(String descripcion_curso) {
        this.descripcion_curso = descripcion_curso;
    }

}
