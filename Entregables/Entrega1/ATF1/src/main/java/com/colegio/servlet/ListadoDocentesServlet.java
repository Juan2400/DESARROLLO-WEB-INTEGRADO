package com.colegio.servlet;

import com.colegio.dao.DocenteDAO;
import com.colegio.model.Docente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listadoDocentes")
public class ListadoDocentesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DocenteDAO docenteDAO = new DocenteDAO();
            List<Docente> docentes = docenteDAO.listarDocentes();
            request.setAttribute("docentes", docentes);
            request.getRequestDispatcher("listadoDocentes.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al listar docentes", e);
        }
    }
}
