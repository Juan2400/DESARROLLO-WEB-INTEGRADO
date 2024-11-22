package com.colegio.bean;

import com.colegio.dao.CursoDocenteDAO;
import com.colegio.dao.CursoDocenteDAOImpl;
import com.colegio.modelo.CursoDocente;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CursoDocenteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private CursoDocenteDAO cursoDocenteDAO;
    private List<CursoDocente> cursoDocentes;
    private CursoDocente selectedCursoDocente = new CursoDocente();

    @PostConstruct
    public void init() {
        cursoDocenteDAO = new CursoDocenteDAOImpl();
        loadCursoDocentes();
    }

    private void loadCursoDocentes() {
        cursoDocentes = cursoDocenteDAO.listarTodos();
    }

    public void prepareNewCursoDocente() {
        this.selectedCursoDocente = new CursoDocente();
    }

    public void saveCursoDocente() {
        try {
            if (this.selectedCursoDocente.getIdCursoDocente() == 0) {
                cursoDocenteDAO.insertar(this.selectedCursoDocente);
                loadCursoDocentes();
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignación Creado", 
                    "Se Asignación de un Curso al Docente exitosamente"));
            } else {
                cursoDocenteDAO.actualizar(this.selectedCursoDocente);
                loadCursoDocentes();
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignación Actualizado", 
                    "Asignación de un Curso al Docente actualizado exitosamente"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                "Ocurrió un error al guardar el Curso Docente"));
        }
    }

    public void deleteCursoDocente(CursoDocente cursoDocente) {
        try {
            cursoDocenteDAO.eliminar(cursoDocente.getIdCursoDocente());
            cursoDocentes.remove(cursoDocente);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "CursoDocente Eliminado", 
                "El Curso-Docente se ha eliminado exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                "Ocurrió un error al eliminar el Curso-Docente"));
        }
    }

    public List<CursoDocente> getCursoDocentes() {
        return cursoDocentes;
    }

    public void setCursoDocentes(List<CursoDocente> cursoDocentes) {
        this.cursoDocentes = cursoDocentes;
    }

    public CursoDocente getSelectedCursoDocente() {
        return selectedCursoDocente;
    }

    public void setSelectedCursoDocente(CursoDocente selectedCursoDocente) {
        this.selectedCursoDocente = selectedCursoDocente;
    }
}
