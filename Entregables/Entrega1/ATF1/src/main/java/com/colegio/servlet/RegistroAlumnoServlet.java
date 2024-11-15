package com.colegio.servlet;


import com.colegio.dao.AlumnoDAO;
import com.colegio.dao.GradoDAO;
import com.colegio.model.Alumno;
import com.colegio.model.Grado;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/registroAlumno")
public class RegistroAlumnoServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            GradoDAO gradoDAO = new GradoDAO();
            List<Grado> grados = gradoDAO.listarGrados();
            request.setAttribute("grados", grados);
            request.getRequestDispatcher("registroAlumno.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar los grados", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Alumno alumno = new Alumno();
            alumno.setNombre(request.getParameter("nombre"));
            alumno.setApellido(request.getParameter("apellido"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(request.getParameter("fechaNacimiento"));
            alumno.setFechaNacimiento(fechaNacimiento);
            alumno.setDireccion(request.getParameter("direccion"));
            alumno.setTelefono(request.getParameter("telefono"));
            alumno.setEmail(request.getParameter("email"));
            alumno.setDni(request.getParameter("dni"));
            
            Grado grado = new Grado();
            grado.setIdGrado(Integer.parseInt(request.getParameter("idGrado")));
            alumno.setGrado(grado);

            AlumnoDAO alumnoDAO = new AlumnoDAO();
            alumnoDAO.registrarAlumno(alumno);
            response.sendRedirect("listadoAlumnos");
        } catch (Exception e) {
            throw new ServletException("Error al registrar alumno", e);
        }
    }
}