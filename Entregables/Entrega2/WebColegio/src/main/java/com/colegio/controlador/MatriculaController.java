package com.colegio.controlador;

import com.colegio.modelo.AlumnoDAO;
import com.colegio.modelo.AlumnoDAOImpl;
import com.colegio.modelo.GradoDAO;
import com.colegio.modelo.GradoDAOImpl;
import com.colegio.modelo.MatriculaDAO;
import com.colegio.modelo.MatriculaDAOImpl;
import com.colegio.modelo.TipoMatriculaDAO;
import com.colegio.modelo.TipoMatriculaDAOImpl;
import com.colegio.modelo.Alumno;
import com.colegio.modelo.Grado;
import com.colegio.modelo.Matricula;
import com.colegio.modelo.TipoMatricula;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MatriculaController", urlPatterns = {"/matricula/*"})
public class MatriculaController extends HttpServlet {

    private MatriculaDAO matriculaDAO;
    private AlumnoDAO alumnoDAO;
    private GradoDAO gradoDAO;
    private TipoMatriculaDAO tipoMatriculaDAO;

    @Override
    public void init() throws ServletException {
        matriculaDAO = new MatriculaDAOImpl();
        alumnoDAO = new AlumnoDAOImpl();
        gradoDAO = new GradoDAOImpl();
        tipoMatriculaDAO = new TipoMatriculaDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/list";
        }
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertMatricula(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateMatricula(request, response);
                    break;
                case "/delete":
                    deleteMatricula(request, response);
                case "/list":
                    listMatriculas(request, response);
                    break;
                default:
                    break;
                
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listMatriculas(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Matricula> listaMatriculas = matriculaDAO.listarTodos();
        request.setAttribute("listaMatriculas", listaMatriculas);
        request.getRequestDispatcher("/WEB-INF/views/matricula-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        request.setAttribute("listaAlumnos", alumnoDAO.listarTodos());
        request.setAttribute("listaGrados", gradoDAO.listarTodos());
        request.setAttribute("listaTiposMatricula", tipoMatriculaDAO.listarTodos());
        request.getRequestDispatcher("/WEB-INF/views/matricula-form.jsp").forward(request, response);
    }

    private void insertMatricula(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            Alumno alumno = new Alumno();
            Grado grado = new Grado();
            TipoMatricula tipoMatricula = new TipoMatricula();

            alumno.setId_alumno(Integer.parseInt(request.getParameter("alumno")));
            grado.setId_grado(Integer.parseInt(request.getParameter("grado")));
            tipoMatricula.setId_tipo_matricula(Integer.parseInt(request.getParameter("tipo_matricula")));

            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha_matricula = fecha.parse(request.getParameter("fecha_matricula"));

            Matricula matricula = new Matricula();
            matricula.setAlumno(alumno);
            matricula.setGrado(grado);
            matricula.setTipo_matricula(tipoMatricula);
            matricula.setAnio(Integer.parseInt(request.getParameter("anio")));
            matricula.setFecha_matricula(fecha_matricula);
            matricula.setObservaciones(request.getParameter("observaciones"));

            matriculaDAO.insertar(matricula);
            response.sendRedirect("list");

        } catch (SQLException e) {
            if (e.getMessage().contains("El alumno ya está matriculado en el año actual")) {
                request.setAttribute("errorMessage", "El alumno ya está matriculado en el año actual.");
                showNewForm(request, response);
            } else {
                throw new ServletException("Error al ingresar la matrícula", e);
            }
        } catch (Exception e) {
            throw new ServletException("Error al procesar la matrícula", e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id_matricula = Integer.parseInt(request.getParameter("id"));
        Matricula existingMatricula = matriculaDAO.obtenerPorId(id_matricula);
        request.setAttribute("matricula", existingMatricula);
        request.setAttribute("listaAlumnos", alumnoDAO.listarTodos());
        request.setAttribute("listaGrados", gradoDAO.listarTodos());
        request.setAttribute("listaTiposMatricula", tipoMatriculaDAO.listarTodos());
        request.getRequestDispatcher("/WEB-INF/views/matricula-form.jsp").forward(request, response);
    }

    private void updateMatricula(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            Alumno alumno = new Alumno();
            Grado grado = new Grado();
            TipoMatricula tipoMatricula = new TipoMatricula();

            alumno.setId_alumno(Integer.parseInt(request.getParameter("alumno")));
            grado.setId_grado(Integer.parseInt(request.getParameter("grado")));
            tipoMatricula.setId_tipo_matricula(Integer.parseInt(request.getParameter("tipo_matricula")));

            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha_matricula = fecha.parse(request.getParameter("fecha_matricula"));

            Matricula matricula = new Matricula();
            matricula.setId_matricula(Integer.parseInt(request.getParameter("id")));
            matricula.setAlumno(alumno);
            matricula.setGrado(grado);
            matricula.setTipo_matricula(tipoMatricula);
            matricula.setAnio(Integer.parseInt(request.getParameter("anio")));
            matricula.setFecha_matricula(fecha_matricula);
            matricula.setObservaciones(request.getParameter("observaciones"));

            matriculaDAO.actualizar(matricula);
            response.sendRedirect("list");

        } catch (SQLException e) {
            if (e.getMessage().contains("El alumno ya está matriculado en el año actual")) {
                request.setAttribute("errorMessage", "El alumno ya está matriculado en el año actual.");
                showEditForm(request, response);
            } else {
                throw new ServletException("Error al actualizar la matrícula", e);
            }
        } catch (Exception e) {
            throw new ServletException("Error al procesar la matrícula", e);
        }
    }

    private void deleteMatricula(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_matricula = Integer.parseInt(request.getParameter("id"));
        matriculaDAO.eliminar(id_matricula);
        response.sendRedirect("list");
    }

}
