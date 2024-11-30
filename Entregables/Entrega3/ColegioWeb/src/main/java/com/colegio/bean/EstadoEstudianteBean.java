package com.colegio.bean;

import com.colegio.dao.EstadoEstudianteDAO;
import com.colegio.dao.EstadoEstudianteDAOImpl;
import com.colegio.modelo.EstadoEstudiante;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EstadoEstudianteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private EstadoEstudianteDAO estadoEstudianteDAO;
    private List<EstadoEstudiante> estadoEstudiantes;
    private EstadoEstudiante selectedEstadoEstudiante;

    @PostConstruct
    public void init() {
        estadoEstudianteDAO = new EstadoEstudianteDAOImpl();
        loadEstadoEstudiantes();
    }

    private void loadEstadoEstudiantes() {
        estadoEstudiantes = estadoEstudianteDAO.listarTodos();
    }

    public void prepareNewEstadoEstudiante() {
        selectedEstadoEstudiante = new EstadoEstudiante();
    }

    public void saveEstadoEstudiante() {
        try {
            if (selectedEstadoEstudiante.getIdEstadoEstudiante() == 0) {
                estadoEstudianteDAO.insertar(selectedEstadoEstudiante);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Estudiante Creado", "El estado estudiante se ha creado exitosamente."));
            } else {
                estadoEstudianteDAO.actualizar(selectedEstadoEstudiante);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Estudiante Actualizado", "El estado estudiante se ha actualizado exitosamente."));
            }
            loadEstadoEstudiantes(); // Recargar la lista
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el estado estudiante."));
            e.printStackTrace();
        }
    }

    public void deleteEstadoEstudiante() {
        try {
            estadoEstudianteDAO.eliminar(selectedEstadoEstudiante.getIdEstadoEstudiante());
            estadoEstudiantes.remove(selectedEstadoEstudiante);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Estudiante Eliminado", "El estado estudiante se ha eliminado exitosamente."));
            selectedEstadoEstudiante = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al eliminar el estado estudiante."));
            e.printStackTrace();
        }
    }

    // Getters y Setters
    public List<EstadoEstudiante> getEstadoEstudiantes() {
        return estadoEstudiantes;
    }

    public void setEstadoEstudiantes(List<EstadoEstudiante> estadoEstudiantes) {
        this.estadoEstudiantes = estadoEstudiantes;
    }

    public EstadoEstudiante getSelectedEstadoEstudiante() {
        return selectedEstadoEstudiante;
    }

    public void setSelectedEstadoEstudiante(EstadoEstudiante selectedEstadoEstudiante) {
        this.selectedEstadoEstudiante = selectedEstadoEstudiante;
    }
}
