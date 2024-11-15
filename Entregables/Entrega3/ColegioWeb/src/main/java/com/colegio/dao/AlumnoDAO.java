package com.colegio.dao;

import com.colegio.modelo.Alumno;
import java.sql.SQLException;
import java.util.List;

public interface AlumnoDAO {
    void insertar(Alumno alumno);
    Alumno obtenerPorId(int idAlumno);
    List<Alumno> listarTodos();
    void actualizar(Alumno alumno);
    void eliminar(int idAlumno);
}
