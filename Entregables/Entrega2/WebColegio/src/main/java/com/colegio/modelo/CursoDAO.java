package com.colegio.modelo;

import com.colegio.modelo.Curso;
import java.sql.SQLException;
import java.util.List;

public interface CursoDAO {
    void insertar(Curso curso) throws SQLException;
    Curso obtenerPorId(int id_curso) throws SQLException;
    List<Curso> listarTodos() throws SQLException;
    void actualizar(Curso curso) throws SQLException;
    void eliminar(int id_curso) throws SQLException;
}
