package com.colegio.servlet;

import com.colegio.dao.CursoDAO;
import com.colegio.dao.DocenteDAO;
import com.colegio.model.Curso;
import com.colegio.model.Docente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/registroCurso")
public class RegistroCursoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DocenteDAO docenteDAO = new DocenteDAO();
            List<Docente> docentes = docenteDAO.listarDocentes();
            request.setAttribute("docentes", docentes);
            request.getRequestDispatcher("registroCurso.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar los docentes", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Curso curso = new Curso();
            curso.setNombreCurso(request.getParameter("nombreCurso"));
            curso.setDescripcionCurso(request.getParameter("descripcionCurso"));
            curso.getDocente().setIdDocente(Integer.parseInt(request.getParameter("idDocente")));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            curso.setFechaInicio(sdf.parse(request.getParameter("fechaInicio")));
            curso.setFechaFin(sdf.parse(request.getParameter("fechaFin")));

            CursoDAO cursoDAO = new CursoDAO();
            cursoDAO.registrarCurso(curso);
            response.sendRedirect("listadoCursos");
        } catch (Exception e) {
            throw new ServletException("Error al registrar curso", e);
        }
    }
}
