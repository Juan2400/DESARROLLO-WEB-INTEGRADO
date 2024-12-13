package com.colegio.bean;

import com.colegio.dao.DocenteDAO;
import com.colegio.dao.DocenteDAOImpl;
import com.colegio.dao.EspecialidadDAO;
import com.colegio.dao.EspecialidadDAOImpl;
import com.colegio.modelo.Docente;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DocenteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private DocenteDAO docenteDAO;  
    private EspecialidadDAO especialidadDAO;  
    
    private List<Docente> docentes;
    private Docente selectedDocente = new Docente();

    @PostConstruct
    public void init() {
        docenteDAO = new DocenteDAOImpl();
        especialidadDAO = new EspecialidadDAOImpl();
        
        loadDocentes();  
    }

    private void loadDocentes() {
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
                showMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Docente registrado correctamente");
            } else {
                docenteDAO.actualizar(selectedDocente);  
                loadDocentes();  
                showMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Docente actualizado correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el docente");
        }
    }

    public void deleteDocente(Docente docente) {
        try {
            docenteDAO.eliminar(docente.getIdDocente()); 
            docentes.remove(docente);  
            showMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Docente eliminado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el docente");
        }
    }

    private void showMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
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

    public EspecialidadDAO getEspecialidadDAO() {
        return especialidadDAO;
    }

    public void setEspecialidadDAO(EspecialidadDAO especialidadDAO) {
        this.especialidadDAO = especialidadDAO;
    }
}
