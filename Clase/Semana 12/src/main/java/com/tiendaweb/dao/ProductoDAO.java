package com.tiendaweb.dao;
import com.tiendaweb.model.Producto;
import java.util.List;

public interface ProductoDAO {
         void insertar(Producto producto);
    List<Producto> listarTodos();
    Producto obtenerPorId(int id);
    void actualizar(Producto producto);
    void eliminar(int id);
    List<Producto> listarPorCategoria(int categoriaId);

}
