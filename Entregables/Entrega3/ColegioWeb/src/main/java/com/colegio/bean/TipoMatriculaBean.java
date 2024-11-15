package com.colegio.bean;

import com.colegio.dao.TipoMatriculaDAO;
import com.colegio.dao.TipoMatriculaDAOImpl;
import com.colegio.modelo.TipoMatricula;
import jakarta.annotation.PostConstruct;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@ViewScoped
public class TipoMatriculaBean implements Serializable {

    private List<TipoMatricula> tiposMatricula;
    private TipoMatricula selectedTipoMatricula = new TipoMatricula();
    private TipoMatriculaDAO tipoMatriculaDAO = new TipoMatriculaDAOImpl();

    @PostConstruct
    public void init(){
        tiposMatricula = tipoMatriculaDAO.listarTodos();
    }

    public void prepareNewTipoMatricula() {
        this.selectedTipoMatricula = new TipoMatricula();
    }

    public List<TipoMatricula> getTiposMatricula() {
        return tiposMatricula;
    }

    public void setTiposMatricula(List<TipoMatricula> tiposMatricula) {
        this.tiposMatricula = tiposMatricula;
    }

    public TipoMatricula getSelectedTipoMatricula() {
        return selectedTipoMatricula;
    }

    public void setSelectedTipoMatricula(TipoMatricula selectedTipoMatricula) {
        this.selectedTipoMatricula = selectedTipoMatricula;
    }
}
