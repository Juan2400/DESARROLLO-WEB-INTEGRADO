package com.colegio.controlador;

import com.colegio.modelo.AlumnoDAO;
import com.colegio.modelo.AlumnoDAOImpl;
import com.colegio.modelo.Alumno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AlumnoController", urlPatterns = {"/alumno/*"})
public class AlumnoController extends HttpServlet {

    private AlumnoDAO alumnoDAO;

    @Override
    public void init() throws ServletException {
        alumnoDAO = new AlumnoDAOImpl();
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
                    insertAlumno(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateAlumno(request, response);
                    break;
                case "/delete":
                    deleteAlumno(request, response);
                    break;
                case "/list":
                    listAlumnos(request, response);
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

    private void listAlumnos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Alumno> listaAlumnos = alumnoDAO.listarTodos();
        request.setAttribute("listaAlumnos", listaAlumnos);
        request.getRequestDispatcher("/WEB-INF/views/alumno-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/alumno-form.jsp").forward(request, response);
    }

    private void insertAlumno(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String telefonoApoderado = request.getParameter("telefonoApoderado");
        String email = request.getParameter("email");

        Alumno nuevoAlumno = new Alumno(0, dni, nombre, apellido, direccion, telefonoApoderado, email);
        alumnoDAO.insertar(nuevoAlumno);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id_alumno = Integer.parseInt(request.getParameter("id"));
        Alumno existingAlumno = alumnoDAO.obtenerPorId(id_alumno);
        request.setAttribute("alumno", existingAlumno);
        request.getRequestDispatcher("/WEB-INF/views/alumno-form.jsp").forward(request, response);
    }

    private void updateAlumno(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_alumno = Integer.parseInt(request.getParameter("id"));
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String telefonoApoderado = request.getParameter("telefonoApoderado");
        String email = request.getParameter("email");

        Alumno alumno = new Alumno(id_alumno, dni, nombre, apellido, direccion, telefonoApoderado, email);
        alumnoDAO.actualizar(alumno);
        response.sendRedirect("list");
    }

    private void deleteAlumno(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_alumno = Integer.parseInt(request.getParameter("id"));
        alumnoDAO.eliminar(id_alumno);
        response.sendRedirect("list");
    }

}
