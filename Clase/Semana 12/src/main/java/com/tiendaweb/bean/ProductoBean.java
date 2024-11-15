package com.tiendaweb.bean;

import com.tiendaweb.dao.ProductoDAO;
import com.tiendaweb.dao.ProductoDAOImpl;
import com.tiendaweb.model.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProductoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ProductoDAO productoDAO;
    private List<Producto> productos;
    private Producto selectedProducto = new Producto();
    
    @PostConstruct
    public void init() {
        productoDAO = new ProductoDAOImpl();
        loadProductos();
    }
    
    private void loadProductos() {
        productos = productoDAO.listarTodos();
    }
    
    public void prepareNewProducto() {
        this.selectedProducto = new Producto();
        
    }
    
    public void saveProducto() {
        try {
            if (this.selectedProducto.getId() == 0) {
                productoDAO.insertar(this.selectedProducto);
                 loadProductos(); // Recargar la lista después de actualizar
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Creado", 
                    "El producto se ha creado exitosamente"));
            } else {
                productoDAO.actualizar(this.selectedProducto);
                loadProductos(); // Recargar la lista después de actualizar
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Actualizado", 
                    "El producto se ha actualizado exitosamente"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                "Ocurrió un error al guardar el producto"));
        }
    }
    
    public void deleteProducto(Producto producto) {
        try {
            productoDAO.eliminar(producto.getId());
            productos.remove(producto);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Eliminado", 
                "El producto se ha eliminado exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                "Ocurrió un error al eliminar el producto"));
        }
    }
    
    // Getters y Setters
    public List<Producto> getProductos() {
        return productos;
    }
    
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    public Producto getSelectedProducto() {
        return selectedProducto;
    }
    
    public void setSelectedProducto(Producto selectedProducto) {
        this.selectedProducto = selectedProducto;
    }
}


