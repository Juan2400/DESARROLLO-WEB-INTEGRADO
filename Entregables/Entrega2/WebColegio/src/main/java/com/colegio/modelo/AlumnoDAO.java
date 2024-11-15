package com.colegio.modelo;

import com.colegio.modelo.Alumno;
import java.sql.SQLException;
import java.util.List;

public interface AlumnoDAO {
    void insertar(Alumno alumno) throws SQLException;
    Alumno obtenerPorId(int id_alumno) throws SQLException;
    List<Alumno> listarTodos() throws SQLException;
    void actualizar(Alumno alumno) throws SQLException;
    void eliminar(int id_alumno) throws SQLException;
}
