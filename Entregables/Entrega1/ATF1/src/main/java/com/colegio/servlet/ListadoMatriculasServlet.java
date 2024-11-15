package com.colegio.servlet;

import com.colegio.dao.MatriculaDAO;
import com.colegio.model.Matricula;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listadoMatriculas")
public class ListadoMatriculasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MatriculaDAO matriculaDAO = new MatriculaDAO();
            List<Matricula> matriculas = matriculaDAO.listarMatriculas();
            request.setAttribute("matriculas", matriculas);
            request.getRequestDispatcher("listadoMatriculas.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al listar las matrículas", e);
        }
    }
}


