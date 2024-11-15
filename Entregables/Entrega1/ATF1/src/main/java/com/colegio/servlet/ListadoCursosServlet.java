package com.colegio.servlet;

import com.colegio.dao.CursoDAO;
import com.colegio.model.Curso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listadoCursos")
public class ListadoCursosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CursoDAO cursoDAO = new CursoDAO();
            List<Curso> cursos = cursoDAO.listarCursos();
            request.setAttribute("cursos", cursos);
            request.getRequestDispatcher("listadoCursos.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al listar cursos", e);
        }
    }
}

