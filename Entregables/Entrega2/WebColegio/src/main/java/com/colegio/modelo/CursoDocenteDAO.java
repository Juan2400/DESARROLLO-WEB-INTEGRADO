package com.colegio.modelo;

import com.colegio.modelo.CursoDocente;
import java.sql.SQLException;
import java.util.List;

public interface CursoDocenteDAO {
    void insertar(CursoDocente cursoDocente) throws SQLException;
    CursoDocente obtenerPorId(int id) throws SQLException;
    List<CursoDocente> listarTodos() throws SQLException;
    void actualizar(CursoDocente cursoDocente) throws SQLException;
    void eliminar(int id) throws SQLException;
}
