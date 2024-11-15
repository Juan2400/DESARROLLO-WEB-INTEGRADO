package com.colegio.controlador;

import com.colegio.modelo.DocenteDAO;
import com.colegio.modelo.DocenteDAOImpl;
import com.colegio.modelo.Docente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DocenteController", urlPatterns = {"/docente/*"})
public class DocenteController extends HttpServlet {

    private DocenteDAO docenteDAO;

    @Override
    public void init() throws ServletException {
        docenteDAO = new DocenteDAOImpl();
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
                    insertDocente(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateDocente(request, response);
                    break;
                case "/delete":
                    deleteDocente(request, response);
                    break;
                case "/list":
                    listDocentes(request, response);
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

    private void listDocentes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Docente> listaDocentes = docenteDAO.listarTodos();
        request.setAttribute("listaDocentes", listaDocentes);
        request.getRequestDispatcher("/WEB-INF/views/docente-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/docente-form.jsp").forward(request, response);
    }

    private void insertDocente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String especialidad = request.getParameter("especialidad");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        Docente nuevoDocente = new Docente(0, dni, nombre, apellido, especialidad, telefono, email);
        docenteDAO.insertar(nuevoDocente);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id_docente = Integer.parseInt(request.getParameter("id"));
        Docente existingDocente = docenteDAO.obtenerPorId(id_docente);
        request.setAttribute("docente", existingDocente);
        request.getRequestDispatcher("/WEB-INF/views/docente-form.jsp").forward(request, response);
    }

    private void updateDocente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_docente = Integer.parseInt(request.getParameter("id"));
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String especialidad = request.getParameter("especialidad");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        Docente docente = new Docente(id_docente, dni, nombre, apellido, especialidad, telefono, email);
        docenteDAO.actualizar(docente);
        response.sendRedirect("list");
    }

    private void deleteDocente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_docente = Integer.parseInt(request.getParameter("id"));
        docenteDAO.eliminar(id_docente);
        response.sendRedirect("list");
    }
}
