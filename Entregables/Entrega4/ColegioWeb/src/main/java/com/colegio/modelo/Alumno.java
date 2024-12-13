package com.colegio.modelo;

public class Alumno {

    private int idAlumno;
    private String codigoEstudiante;
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String sexo; // 'M' o 'F'
    private String telefonoReferencia;
    private Responsable padre;
    private Responsable madre;
    private Responsable apoderado;
    private EstadoEstudiante estadoEstudiante;
    private Grado grado;

    public Alumno() {
        this.padre = new Responsable();
        this.madre = new Responsable();
        this.apoderado = new Responsable();
        this.estadoEstudiante = new EstadoEstudiante();
        this.grado = new Grado();
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefonoReferencia() {
        return telefonoReferencia;
    }

    public void setTelefonoReferencia(String telefonoReferencia) {
        this.telefonoReferencia = telefonoReferencia;
    }

    public Responsable getPadre() {
        return padre;
    }

    public void setPadre(Responsable padre) {
        this.padre = padre;
    }

    public Responsable getMadre() {
        return madre;
    }

    public void setMadre(Responsable madre) {
        this.madre = madre;
    }

    public Responsable getApoderado() {
        return apoderado;
    }

    public void setApoderado(Responsable apoderado) {
        this.apoderado = apoderado;
    }

    public EstadoEstudiante getEstadoEstudiante() {
        return estadoEstudiante;
    }

    public void setEstadoEstudiante(EstadoEstudiante estadoEstudiante) {
        this.estadoEstudiante = estadoEstudiante;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

}
