package com.colegio.bean;

import com.colegio.dao.DocenteDAO;
import com.colegio.dao.DocenteDAOImpl;
import com.colegio.modelo.Docente;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.List;

@Named
@ViewScoped
public class DocenteBean implements Serializable {

    private List<Docente> docentes;
    private Docente selectedDocente = new Docente();
    private DocenteDAO docenteDAO = new DocenteDAOImpl();

    @PostConstruct
    public void init(){
        docentes = docenteDAO.listarTodos();
    }

    public void prepareNewDocente() {
        this.selectedDocente = new Docente();
    }

    public void saveDocente() {
        try {
            if (selectedDocente.getIdDocente() == 0) {
                docenteDAO.insertar(selectedDocente);
                docentes.add(this.selectedDocente);
            } else {
                docenteDAO.actualizar(selectedDocente);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Éxito", "Registro Correcto"));

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "No se pudo grabar"));
        }
    }

    public void deleteDocente(Docente docente){
        docenteDAO.eliminar(docente.getIdDocente());
        docentes.remove(docente);
    }
    
    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public Docente getSelectedDocente() {
        return selectedDocente;
    }

    public void setSelectedDocente(Docente selectedDocente) {
        this.selectedDocente = selectedDocente;
    }

    public DocenteDAO getDocenteDAO() {
        return docenteDAO;
    }

    public void setDocenteDAO(DocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }
}
