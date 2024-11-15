package com.colegio.controlador;

import com.colegio.modelo.CursoDocenteDAO;
import com.colegio.modelo.CursoDocenteDAOImpl;
import com.colegio.modelo.CursoDAO;
import com.colegio.modelo.CursoDAOImpl;
import com.colegio.modelo.DocenteDAO;
import com.colegio.modelo.DocenteDAOImpl;
import com.colegio.modelo.GradoDAO;
import com.colegio.modelo.GradoDAOImpl;
import com.colegio.modelo.Curso;
import com.colegio.modelo.CursoDocente;
import com.colegio.modelo.Docente;
import com.colegio.modelo.Grado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CursoDocenteController", urlPatterns = {"/curso-docente/*"})
public class CursoDocenteController extends HttpServlet {

    private CursoDocenteDAO cursoDocenteDAO;
    private CursoDAO cursoDAO;
    private DocenteDAO docenteDAO;
    private GradoDAO gradoDAO;

    @Override
    public void init() throws ServletException {
        cursoDocenteDAO = new CursoDocenteDAOImpl();
        cursoDAO = new CursoDAOImpl();
        docenteDAO = new DocenteDAOImpl();
        gradoDAO = new GradoDAOImpl();
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
                    insertCursoDocente(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCursoDocente(request, response);
                    break;
                case "/delete":
                    deleteCursoDocente(request, response);
                    break;
                case "/list":
                    listCursosDocentes(request, response);
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

    private void listCursosDocentes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<CursoDocente> listaCursosDocentes = cursoDocenteDAO.listarTodos();
        request.setAttribute("listaCursosDocentes", listaCursosDocentes);
        request.getRequestDispatcher("/WEB-INF/views/curso-docente-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        request.setAttribute("listaCursos", cursoDAO.listarTodos());
        request.setAttribute("listaDocentes", docenteDAO.listarTodos());
        request.setAttribute("listaGrados", gradoDAO.listarTodos());
        request.getRequestDispatcher("/WEB-INF/views/curso-docente-form.jsp").forward(request, response);
    }

    private void insertCursoDocente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            Curso curso = new Curso();
            curso.setId_curso(Integer.parseInt(request.getParameter("curso")));

            Docente docente = new Docente();
            docente.setId_docente(Integer.parseInt(request.getParameter("docente")));

            Grado grado = new Grado();
            grado.setId_grado(Integer.parseInt(request.getParameter("grado")));

            CursoDocente nuevoCursoDocente = new CursoDocente();
            nuevoCursoDocente.setCurso(curso);
            nuevoCursoDocente.setDocente(docente);
            nuevoCursoDocente.setGrado(grado);

            cursoDocenteDAO.insertar(nuevoCursoDocente);
            response.sendRedirect("list");
        } catch (SQLException e) {
            if (e.getMessage().contains("La asignación de este curso al docente ya existe.")) {
                request.setAttribute("errorMessage", "La asignación de este curso al docente ya existe.");
                showNewForm(request, response);
            } else {
                throw new ServletException("Error al ingresar la asignación del curso", e);
            }
        } catch (Exception e) {
            throw new ServletException("Error al procesar la asignación del curso", e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CursoDocente existingCursoDocente = cursoDocenteDAO.obtenerPorId(id);
        request.setAttribute("cursoDocente", existingCursoDocente);
        request.setAttribute("listaCursos", cursoDAO.listarTodos());
        request.setAttribute("listaDocentes", docenteDAO.listarTodos());
        request.setAttribute("listaGrados", gradoDAO.listarTodos());
        request.getRequestDispatcher("/WEB-INF/views/curso-docente-form.jsp").forward(request, response);
    }

    private void updateCursoDocente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Curso curso = new Curso();
            curso.setId_curso(Integer.parseInt(request.getParameter("curso")));

            Docente docente = new Docente();
            docente.setId_docente(Integer.parseInt(request.getParameter("docente")));

            Grado grado = new Grado();
            grado.setId_grado(Integer.parseInt(request.getParameter("grado")));

            CursoDocente cursoDocente = new CursoDocente();
            cursoDocente.setId(id);
            cursoDocente.setCurso(curso);
            cursoDocente.setDocente(docente);
            cursoDocente.setGrado(grado);

            cursoDocenteDAO.actualizar(cursoDocente);
            response.sendRedirect("list");
        } catch (SQLException e) {
            if (e.getMessage().contains("La asignación de este curso al docente ya existe.")) {
                request.setAttribute("errorMessage", "La asignación de este curso al docente ya existe.");
                showEditForm(request, response);
            } else {
                throw new ServletException("Error al actualizar la asignación del curso", e);
            }
        } catch (Exception e) {
            throw new ServletException("Error al procesar la asignación del curso", e);
        }
    }

    private void deleteCursoDocente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cursoDocenteDAO.eliminar(id);
        response.sendRedirect("list");
    }
}
