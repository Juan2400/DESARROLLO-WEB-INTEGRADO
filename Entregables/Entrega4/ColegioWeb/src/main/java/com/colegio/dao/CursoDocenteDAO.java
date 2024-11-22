package com.colegio.dao;

import com.colegio.modelo.CursoDocente;
import java.sql.SQLException;
import java.util.List;

public interface CursoDocenteDAO {
    void insertar(CursoDocente cursoDocente);
    CursoDocente obtenerPorId(int idCursoDocente);
    List<CursoDocente> listarTodos();
    void actualizar(CursoDocente cursoDocente);
    void eliminar(int idCursoDocente);
}
//throws SQLException