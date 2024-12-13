package com.colegio.bean;

import com.colegio.dao.ResponsableDAO;
import com.colegio.dao.ResponsableDAOImpl;
import com.colegio.modelo.Responsable;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ResponsableBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ResponsableDAO responsableDAO;
    private List<Responsable> responsables;
    private Responsable selectedResponsable;

    @PostConstruct
    public void init() {
        responsableDAO = new ResponsableDAOImpl();
        loadResponsables();
        prepareNewResponsable();
    }

    private void loadResponsables() {
        responsables = responsableDAO.listarTodos();
    }

    public void prepareNewResponsable() {
        selectedResponsable = new Responsable();
    }

    public void saveResponsable() {
        try {
            if (selectedResponsable.getIdResponsable() == 0) {
                responsableDAO.insertar(selectedResponsable);
                loadResponsables();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Responsable Creado", "Se ha creado el responsable correctamente."));
            } else {
                responsableDAO.actualizar(selectedResponsable);
                loadResponsables();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Responsable Actualizado", "Se ha actualizado el responsable correctamente."));
            }
            prepareNewResponsable();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el responsable."));
            e.printStackTrace();
        }
    }

    public void deleteResponsable() {
        try {
            responsableDAO.eliminar(selectedResponsable.getIdResponsable());
            responsables.remove(selectedResponsable);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Responsable Eliminado", "Se ha eliminado el responsable correctamente."));
            prepareNewResponsable();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al eliminar el responsable."));
            e.printStackTrace();
        }
    }

    public List<Responsable> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<Responsable> responsables) {
        this.responsables = responsables;
    }

    public Responsable getSelectedResponsable() {
        return selectedResponsable;
    }

    public void setSelectedResponsable(Responsable selectedResponsable) {
        this.selectedResponsable = selectedResponsable;
    }
}
