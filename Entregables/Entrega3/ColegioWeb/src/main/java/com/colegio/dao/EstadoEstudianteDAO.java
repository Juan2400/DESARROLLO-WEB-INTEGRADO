package com.colegio.dao;

import com.colegio.modelo.EstadoEstudiante;
import java.sql.SQLException;
import java.util.List;

public interface EstadoEstudianteDAO {
    void insertar(EstadoEstudiante estadoEstudiante);
    EstadoEstudiante obtenerPorId(int idEstadoEstudiante);
    List<EstadoEstudiante> listarTodos();
    void actualizar(EstadoEstudiante estadoEstudiante);
    void eliminar(int idEstadoEstudiante);
}
