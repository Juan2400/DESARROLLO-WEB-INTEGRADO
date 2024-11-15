package com.utp.modelo;

import java.sql.SQLException;
import java.util.List;

public interface CategoriaProductoDAO {
    void insertar(CategoriaProducto categoria) throws SQLException;
    CategoriaProducto obtenerPorId(int id) throws SQLException;
    List<CategoriaProducto> listarTodos()  throws SQLException;
    void actualizar(CategoriaProducto categoria) throws SQLException;
    void eliminar(int id) throws SQLException;

}
