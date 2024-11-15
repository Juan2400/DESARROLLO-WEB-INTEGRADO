package com.colegio.modelo;

public class Alumno extends Persona {

    private int id_alumno;
    private String direccion;

    public Alumno() {
    }

    public Alumno(int id_alumno, String dni, String nombre, String apellido, String direccion, String telefonoApoderado, String email) {
        super(dni, nombre, apellido, telefonoApoderado, email);
        this.id_alumno = id_alumno;
        this.direccion = direccion;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
