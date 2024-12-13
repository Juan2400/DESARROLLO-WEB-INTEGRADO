package com.colegio.bean;

import com.colegio.dao.EspecialidadDAO;
import com.colegio.dao.EspecialidadDAOImpl;
import com.colegio.modelo.Especialidad;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EspecialidadBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private EspecialidadDAO especialidadDAO;
    private List<Especialidad> especialidades;
    private Especialidad selectedEspecialidad;

    @PostConstruct
    public void init() {
        especialidadDAO = new EspecialidadDAOImpl();
        loadEspecialidades();
        selectedEspecialidad = new Especialidad();
    }

    private void loadEspecialidades() {
        try {
            especialidades = especialidadDAO.listarTodos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar las especialidades"));
        }
    }

    public void prepareNewEspecialidad() {
        selectedEspecialidad = new Especialidad();
    }

    public void saveEspecialidad() {
        try {
            if (selectedEspecialidad.getIdEspecialidad() == 0) {
                especialidadDAO.insertar(selectedEspecialidad);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Especialidad creada exitosamente"));
            } else {
                especialidadDAO.actualizar(selectedEspecialidad);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Especialidad actualizada exitosamente"));
            }
            loadEspecialidades();
            selectedEspecialidad = new Especialidad(); // Reinicia el objeto
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la especialidad"));
        }
    }

    public void deleteEspecialidad() {
        try {
            especialidadDAO.eliminar(selectedEspecialidad.getIdEspecialidad());
            especialidades.remove(selectedEspecialidad);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Especialidad eliminada exitosamente"));
            selectedEspecialidad = new Especialidad(); // Reinicia el objeto
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la especialidad"));
        }
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public Especialidad getSelectedEspecialidad() {
        return selectedEspecialidad;
    }

    public void setSelectedEspecialidad(Especialidad selectedEspecialidad) {
        this.selectedEspecialidad = selectedEspecialidad;
    }
}
