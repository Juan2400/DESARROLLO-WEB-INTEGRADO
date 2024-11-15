package com.colegio.modelo;

import com.colegio.modelo.Docente;
import java.sql.SQLException;
import java.util.List;

public interface DocenteDAO {
    void insertar(Docente docente) throws SQLException;
    Docente obtenerPorId(int id_docente) throws SQLException;
    List<Docente> listarTodos() throws SQLException;
    void actualizar(Docente docente) throws SQLException;
    void eliminar(int id_docente) throws SQLException;
}
