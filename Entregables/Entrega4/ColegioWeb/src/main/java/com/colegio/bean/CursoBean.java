package com.colegio.bean;

import com.colegio.dao.CursoDAO;
import com.colegio.dao.CursoDAOImpl;
import com.colegio.modelo.Curso;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@ViewScoped
public class CursoBean implements Serializable {

    private List<Curso> cursos;
    private Curso selectedCurso = new Curso();
    private CursoDAO cursoDAO = new CursoDAOImpl();

    @PostConstruct
    public void init(){
        cursos = cursoDAO.listarTodos();
    }

    public void prepareNewCurso() {
        this.selectedCurso = new Curso();
    }

    public void saveCurso() {
        try {
            if (this.selectedCurso.getIdCurso() == 0) {
                cursoDAO.insertar(this.selectedCurso);
                cursos.add(this.selectedCurso);
            } else {
                cursoDAO.actualizar(this.selectedCurso);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso guardado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el curso"));
            e.printStackTrace(); 
        }
    }

    public void deleteCurso(Curso curso){
        cursoDAO.eliminar(curso.getIdCurso());
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getSelectedCurso() {
        return selectedCurso;
    }

    public void setSelectedCurso(Curso selectedCurso) {
        this.selectedCurso = selectedCurso;
    }

    public CursoDAO getCursoDAO() {
        return cursoDAO;
    }

    public void setCursoDAO(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }
}
