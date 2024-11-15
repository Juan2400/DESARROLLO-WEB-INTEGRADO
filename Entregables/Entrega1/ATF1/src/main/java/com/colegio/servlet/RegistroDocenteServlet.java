package com.colegio.servlet;


import com.colegio.dao.DocenteDAO;
import com.colegio.model.Docente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registroDocente")
public class RegistroDocenteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registroDocente.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Docente docente = new Docente();
            docente.setNombre(request.getParameter("nombre"));
            docente.setApellido(request.getParameter("apellido"));
            docente.setEspecialidad(request.getParameter("especialidad"));
            docente.setTelefono(request.getParameter("telefono"));
            docente.setEmail(request.getParameter("email"));

            DocenteDAO docenteDAO = new DocenteDAO();
            docenteDAO.registrarDocente(docente);
            response.sendRedirect("listadoDocentes");
        } catch (Exception e) {
            throw new ServletException("Error al registrar docente", e);
        }
    }
}
