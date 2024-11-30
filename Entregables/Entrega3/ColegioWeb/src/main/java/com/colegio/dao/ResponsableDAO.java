package com.colegio.dao;

import com.colegio.modelo.Responsable;
import java.sql.*;
import java.util.List;

public interface ResponsableDAO {
    void insertar(Responsable responsable);
    Responsable obtenerPorId(int idResponsable);
    List<Responsable> listarTodos();
    void actualizar(Responsable responsable);
    void eliminar(int idResponsable);
}
