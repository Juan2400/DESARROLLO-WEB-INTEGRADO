package com.colegio.dao;

import com.colegio.modelo.Matricula;
import java.sql.SQLException;
import java.util.List;

public interface MatriculaDAO {
    void insertar(Matricula matricula);
    Matricula obtenerPorId(int idMatricula);
    List<Matricula> listarTodos();
    void actualizar(Matricula matricula);
    void eliminar(int idMatricula);
}
//throws SQLException