package com.colegio.controlador;

import com.colegio.modelo.CursoDAO;
import com.colegio.modelo.CursoDAOImpl;
import com.colegio.modelo.Curso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CursoController", urlPatterns = {"/curso/*"})
public class CursoController extends HttpServlet {

    private CursoDAO cursoDAO;

    @Override
    public void init() throws ServletException {
        cursoDAO = new CursoDAOImpl();
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
                    insertCurso(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCurso(request, response);
                    break;
                case "/delete":
                    deleteCurso(request, response);
                    break;
                case "/list":
                     listCursos(request, response);
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

    private void listCursos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Curso> listaCursos = cursoDAO.listarTodos();
        request.setAttribute("listaCursos", listaCursos);
        request.getRequestDispatcher("/WEB-INF/views/curso-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/curso-form.jsp").forward(request, response);
    }

    private void insertCurso(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nombre_curso = request.getParameter("nombre_curso");
        String descripcion_curso = request.getParameter("descripcion_curso");

        Curso nuevoCurso = new Curso(0, nombre_curso, descripcion_curso);
        cursoDAO.insertar(nuevoCurso);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id_curso = Integer.parseInt(request.getParameter("id"));
        Curso existingCurso = cursoDAO.obtenerPorId(id_curso);
        request.setAttribute("curso", existingCurso);
        request.getRequestDispatcher("/WEB-INF/views/curso-form.jsp").forward(request, response);
    }

    private void updateCurso(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_curso = Integer.parseInt(request.getParameter("id"));
        String nombre_curso = request.getParameter("nombre_curso");
        String descripcion_curso = request.getParameter("descripcion_curso");

        Curso curso = new Curso(id_curso, nombre_curso, descripcion_curso);
        cursoDAO.actualizar(curso);
        response.sendRedirect("list");
    }

    private void deleteCurso(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_curso = Integer.parseInt(request.getParameter("id"));
        cursoDAO.eliminar(id_curso);
        response.sendRedirect("list");
    }

}
