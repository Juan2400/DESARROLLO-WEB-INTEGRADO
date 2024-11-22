package com.colegio.dao;

import com.colegio.modelo.Docente;
import java.sql.SQLException;
import java.util.List;

public interface DocenteDAO {
    void insertar(Docente docente);
    Docente obtenerPorId(int idDocente);
    List<Docente> listarTodos();
    void actualizar(Docente docente);
    void eliminar(int idDocente);
}
