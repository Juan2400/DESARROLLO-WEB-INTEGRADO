package com.colegio.modelo;

import com.colegio.modelo.Matricula;
import java.sql.SQLException;
import java.util.List;

public interface MatriculaDAO {
    void insertar(Matricula matricula) throws SQLException;
    Matricula obtenerPorId(int id_matricula) throws SQLException;
    List<Matricula> listarTodos() throws SQLException;
    void actualizar(Matricula matricula) throws SQLException;
    void eliminar(int id_matricula) throws SQLException;
}
