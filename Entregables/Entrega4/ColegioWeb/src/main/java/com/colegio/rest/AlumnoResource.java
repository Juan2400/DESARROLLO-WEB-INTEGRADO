package com.colegio.rest;

import com.colegio.dao.AlumnoDAO;
import com.colegio.dao.AlumnoDAOImpl;
import com.colegio.modelo.Alumno;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/alumnos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlumnoResource {

    private final AlumnoDAO alumnoDAO = new AlumnoDAOImpl();

    // Obtener todos los alumnos
    @GET
    public List<Alumno> getAlumnos() {
        return alumnoDAO.listarTodos();
    }

    // Obtener un alumno por ID
    @GET
    @Path("/{id}")
    public Response getAlumno(@PathParam("id") int id) {
        Alumno alumno = alumnoDAO.obtenerPorId(id);
        if (alumno != null) {
            return Response.ok(alumno).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Crear un nuevo alumno
    @POST
    public Response createAlumno(Alumno alumno) {
        alumnoDAO.insertar(alumno);
        return Response.status(Response.Status.CREATED).entity(alumno).build();
    }

    // Actualizar un alumno existente
    @PUT
    @Path("/{id}")
    public Response updateAlumno(@PathParam("id") int id, Alumno alumno) {
        alumno.setIdAlumno(id);
        alumnoDAO.actualizar(alumno);
        return Response.ok(alumno).build();
    }

    // Eliminar un alumno
    @DELETE
    @Path("/{id}")
    public Response deleteAlumno(@PathParam("id") int id) {
        alumnoDAO.eliminar(id);
        return Response.noContent().build();
    }
}
