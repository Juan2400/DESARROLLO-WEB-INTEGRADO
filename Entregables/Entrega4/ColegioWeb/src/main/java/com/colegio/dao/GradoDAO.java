package com.colegio.dao;

import com.colegio.modelo.Grado;
import java.util.List;

public interface GradoDAO {
    Grado obtenerPorId(int idGrado);
    List<Grado> listarTodos();
    List<Grado> obtenerGradosMayoresA(int gradoActual);
    Grado obtenerPrimerGrado();
}
