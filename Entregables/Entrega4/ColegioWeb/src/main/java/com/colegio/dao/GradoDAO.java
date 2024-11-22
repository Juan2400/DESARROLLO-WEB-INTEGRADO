package com.colegio.dao;

import com.colegio.modelo.Grado;
import java.sql.SQLException;
import java.util.List;

public interface GradoDAO {
    Grado obtenerPorId(int idGrado);
    List<Grado> listarTodos();
}
