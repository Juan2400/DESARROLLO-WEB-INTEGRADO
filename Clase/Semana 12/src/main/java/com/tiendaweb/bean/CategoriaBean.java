package com.tiendaweb.bean;

import com.tiendaweb.dao.CategoriaDAO;
import com.tiendaweb.dao.CategoriaDAOImpl;
import com.tiendaweb.model.Categoria;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.view.facelets.FaceletContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CategoriaBean implements Serializable {

    private List<Categoria> categorias;
    private Categoria selectedCategoria = new Categoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

    @PostConstruct
    public void init() {
        categorias = categoriaDAO.listarTodas();
    }

    public void prepareNewCategoria() {
        this.selectedCategoria = new Categoria();
    }

    public void saveCategoria() {
        try {
            if (selectedCategoria.getId() == 0) {
                categoriaDAO.insertar(selectedCategoria);
                categorias.add(this.selectedCategoria);
            } else {
                categoriaDAO.actualizar(selectedCategoria);
            }
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "Exito",
                            "Registro Correcto"
                    )
                    );

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Error",
                            "No se pudo grabar"
                    )
                    );

        }
    }

    public void deleteCategoria(Categoria categoria) {
        categoriaDAO.eliminar(categoria.getId());
        categorias.remove(categoria);
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

}
