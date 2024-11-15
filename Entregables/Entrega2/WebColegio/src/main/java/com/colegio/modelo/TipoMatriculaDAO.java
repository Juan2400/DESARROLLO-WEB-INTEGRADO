package com.colegio.modelo;

import com.colegio.modelo.TipoMatricula;
import java.sql.SQLException;
import java.util.List;

public interface TipoMatriculaDAO {
    TipoMatricula obtenerPorId(int id_tipo_matricula) throws SQLException;
    List<TipoMatricula> listarTodos() throws SQLException;
}
