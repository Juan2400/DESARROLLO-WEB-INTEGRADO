package com.colegio.dao;

import com.colegio.modelo.Docente;
import java.sql.SQLException;
import java.util.List;

public interface DocenteDAO {
    void insertar(Docente docente);
    Docente obtenerPorId(int id_docente);
    List<Docente> listarTodos();
    List<Docente> listarDocentesPorEspecialidadYEstado(int idEspecialidad, String estado);
    void actualizar(Docente docente);
    void eliminar(int id_docente);
}
