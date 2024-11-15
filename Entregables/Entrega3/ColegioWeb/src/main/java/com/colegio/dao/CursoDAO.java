package com.colegio.dao;

import com.colegio.modelo.Curso;
import java.sql.SQLException;
import java.util.List;

public interface CursoDAO {
    void insertar(Curso curso);
    Curso obtenerPorId(int idCurso);
    List<Curso> listarTodos();
    void actualizar(Curso curso);
    void eliminar(int idCurso);
}
