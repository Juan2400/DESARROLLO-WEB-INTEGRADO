package com.colegio.bean;

import com.colegio.dao.GradoDAO;
import com.colegio.dao.GradoDAOImpl;
import com.colegio.modelo.Grado;
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
public class GradoBean implements Serializable {

    private List<Grado> grados;
    private Grado selectedGrado = new Grado();
    private GradoDAO gradoDAO = new GradoDAOImpl();

    @PostConstruct
    public void init()  {
        grados = gradoDAO.listarTodos();
    }

    public void prepareNewGrado() {
        this.selectedGrado = new Grado();
    }


    public List<Grado> getGrados() {
        return grados;
    }

    public void setGrados(List<Grado> grados) {
        this.grados = grados;
    }

    public Grado getSelectedGrado() {
        return selectedGrado;
    }

    public void setSelectedGrado(Grado selectedGrado) {
        this.selectedGrado = selectedGrado;
    }
}
