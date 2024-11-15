package com.colegio.modelo;

public class CursoDocente {

    private int id;
    private Curso curso;
    private Docente docente;
    private Grado grado;

    public CursoDocente() {
        this.curso = new Curso();
        this.docente = new Docente();
        this.grado = new Grado();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

}
