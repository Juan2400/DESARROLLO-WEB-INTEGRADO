package com.tiendaweb.dao;
import com.tiendaweb.model.Categoria;
import java.util.List;

public interface CategoriaDAO {

    void insertar(Categoria categoria);

    List<Categoria> listarTodas();

    Categoria obtenerPorId(int id);

    void actualizar(Categoria categoria);

    void eliminar(int id);

}
