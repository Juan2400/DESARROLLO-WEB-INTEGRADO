package com.colegio.dao;

import com.colegio.modelo.TipoMatricula;
import java.sql.SQLException;
import java.util.List;

public interface TipoMatriculaDAO {
    TipoMatricula obtenerPorId(int idTipoMatricula);
    List<TipoMatricula> listarTodos();
}
