package com.colegio.dao;

import com.colegio.modelo.Especialidad;
import java.util.List;

public interface EspecialidadDAO {

    void insertar(Especialidad especialidad);

    Especialidad obtenerPorId(int idEspecialidad);

    List<Especialidad> listarTodos();

    void actualizar(Especialidad especialidad);

    void eliminar(int idEspecialidad);
}
