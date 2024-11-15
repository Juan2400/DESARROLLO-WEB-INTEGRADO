package com.colegio.modelo;

import com.colegio.modelo.Grado;
import java.sql.SQLException;
import java.util.List;

public interface GradoDAO {
    Grado obtenerPorId(int id_grado) throws SQLException;
    List<Grado> listarTodos() throws SQLException;
}
