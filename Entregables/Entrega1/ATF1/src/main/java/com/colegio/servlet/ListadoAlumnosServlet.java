package com.colegio.servlet;

import com.colegio.dao.AlumnoDAO;
import com.colegio.model.Alumno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listadoAlumnos")
public class ListadoAlumnosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AlumnoDAO alumnoDAO = new AlumnoDAO();
            List<Alumno> alumnos = alumnoDAO.listarAlumnos();
            request.setAttribute("alumnos", alumnos);
            request.getRequestDispatcher("listadoAlumnos.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al listar los alumnos", e);
        }
    }
}
