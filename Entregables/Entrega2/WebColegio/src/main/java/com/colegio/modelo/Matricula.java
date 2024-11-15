package com.colegio.modelo;

import java.util.Date;

public class Matricula {
    private int id_matricula;
    private Alumno alumno;
    private Grado grado;
    private TipoMatricula tipo_matricula;
    private int anio;
    private Date fecha_matricula;
    private String observaciones;

    public Matricula() {
        this.alumno = new Alumno();
        this.grado = new Grado();
        this.tipo_matricula = new TipoMatricula();
    }
    
    public int getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(int id_matricula) {
        this.id_matricula = id_matricula;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public TipoMatricula getTipo_matricula() {
        return tipo_matricula;
    }

    public void setTipo_matricula(TipoMatricula tipo_matricula) {
        this.tipo_matricula = tipo_matricula;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Date getFecha_matricula() {
        return fecha_matricula;
    }

    public void setFecha_matricula(Date fecha_matricula) {
        this.fecha_matricula = fecha_matricula;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
