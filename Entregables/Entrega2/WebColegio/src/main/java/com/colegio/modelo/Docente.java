package com.colegio.modelo;

public class Docente extends Persona {
    private int id_docente;
    private String especialidad;

    public Docente() {
    }

    public Docente(int id_docente, String dni, String nombre, String apellido, String especialidad, String telefono, String email) {
        super(dni, nombre, apellido, telefono, email);
        this.id_docente = id_docente;
        this.especialidad = especialidad;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
