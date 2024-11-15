package com.colegio.servlet;

import com.colegio.dao.AlumnoDAO;
import com.colegio.dao.CursoDAO;
import com.colegio.dao.MatriculaDAO;
import com.colegio.model.Alumno;
import com.colegio.model.Curso;
import com.colegio.model.Matricula;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/registroMatricula")
public class RegistroMatriculaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AlumnoDAO alumnoDAO = new AlumnoDAO();
            CursoDAO cursoDAO = new CursoDAO();

            List<Alumno> alumnos = alumnoDAO.listarAlumnos();
            List<Curso> cursos = cursoDAO.listarCursos();

            request.setAttribute("alumnos", alumnos);
            request.setAttribute("cursos", cursos);
            request.getRequestDispatcher("registroMatricula.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar los datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Matricula matricula = new Matricula();
            matricula.getAlumno().setIdAlumno(Integer.parseInt(request.getParameter("idAlumno")));
            matricula.getCurso().setIdCurso(Integer.parseInt(request.getParameter("idCurso")));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaMatricula = sdf.parse(request.getParameter("fechaMatricula"));
            matricula.setFechaMatricula(fechaMatricula);
            matricula.setTipoMatricula(request.getParameter("tipoMatricula"));
            matricula.setObservaciones(request.getParameter("observaciones"));

            MatriculaDAO matriculaDAO = new MatriculaDAO();
            matriculaDAO.registrarMatricula(matricula);
            response.sendRedirect("listadoMatriculas");
        } catch (Exception e) {
            throw new ServletException("Error al registrar la matrícula", e);
        }
    }
}


