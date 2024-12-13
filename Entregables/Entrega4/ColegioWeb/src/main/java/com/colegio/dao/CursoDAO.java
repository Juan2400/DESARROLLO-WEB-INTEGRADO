package com.colegio.dao;

import com.colegio.modelo.Curso;
import java.sql.*;
import java.util.List;

public interface CursoDAO {
    void insertar(Curso curso);
    Curso obtenerPorId(int id_curso);
    List<Curso> listarTodos();
    void actualizar(Curso curso);
    void eliminar(int id_curso) ;
}
