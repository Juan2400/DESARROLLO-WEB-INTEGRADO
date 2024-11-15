package com.colegio.bean;

import com.colegio.dao.MatriculaDAO;
import com.colegio.dao.MatriculaDAOImpl;
import com.colegio.modelo.Matricula;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MatriculaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private MatriculaDAO matriculaDAO;
    private List<Matricula> matriculas;
    private Matricula selectedMatricula = new Matricula();

    @PostConstruct
    public void init() {
        matriculaDAO = new MatriculaDAOImpl();
        loadMatriculas();
    }

    private void loadMatriculas() {
        matriculas = matriculaDAO.listarTodos();
    }

    public void prepareNewMatricula() {
        this.selectedMatricula = new Matricula();
    }

    public void saveMatricula() {
        try {
            if (this.selectedMatricula.getIdMatricula() == 0) {
                matriculaDAO.insertar(this.selectedMatricula);
                loadMatriculas();
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Matrícula Creada", 
                    "La matrícula se ha creado exitosamente"));
            } else {
                matriculaDAO.actualizar(this.selectedMatricula);
                loadMatriculas();
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Matrícula Actualizada", 
                    "La matrícula se ha actualizado exitosamente"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                "Ocurrió un error al guardar la matrícula"));
        }
    }

    public void deleteMatricula(Matricula matricula) {
        try {
            matriculaDAO.eliminar(matricula.getIdMatricula());
            matriculas.remove(matricula);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Matrícula Eliminada", 
                "La matrícula se ha eliminado exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                "Ocurrió un error al eliminar la matrícula"));
        }
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public Matricula getSelectedMatricula() {
        return selectedMatricula;
    }

    public void setSelectedMatricula(Matricula selectedMatricula) {
        this.selectedMatricula = selectedMatricula;
    }
}
